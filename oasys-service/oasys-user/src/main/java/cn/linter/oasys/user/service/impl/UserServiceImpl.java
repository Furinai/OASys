package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.common.exception.BusinessException;
import cn.linter.oasys.user.dao.UserDao;
import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.entity.User;
import cn.linter.oasys.user.service.PermissionService;
import cn.linter.oasys.user.service.RoleService;
import cn.linter.oasys.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserDao userDao, RoleService roleService, PermissionService permissionService) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public User query(String username) {
        return userDao.select(username);
    }

    @Override
    public PageInfo<User> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(userDao.list());
    }

    @Override
    public List<Role> listRoleByUsername(String username) {
        return roleService.listByUsername(username);
    }

    @Override
    public List<Permission> listPermissionByUsername(String username, boolean treeMode) {
        return permissionService.listByUsername(username, treeMode);
    }

    @Override
    public User create(User user) {
        if (userDao.select(user.getUsername()) != null) {
            throw new BusinessException(ResultStatus.USERNAME_ALREADY_EXISTS);
        }
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userDao.insert(user);
        userDao.insertRole(user.getId(), user.getRoles());
        return user;
    }

    @Override
    public User update(User user) {
        User oldUser = userDao.select(user.getUsername());
        if (oldUser != null && !oldUser.getUsername().equals(user.getUsername())) {
            throw new BusinessException(ResultStatus.USERNAME_ALREADY_EXISTS);
        }
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        user.setUpdateTime(LocalDateTime.now());
        userDao.update(user);
        long id = user.getId();
        List<Role> roles = user.getRoles();
        userDao.deleteRole(id);
        userDao.insertRole(id, roles);
        return query(user.getUsername());
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id) > 0;
    }

}