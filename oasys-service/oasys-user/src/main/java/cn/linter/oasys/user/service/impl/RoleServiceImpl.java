package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.user.dao.RoleDao;
import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.service.PermissionService;
import cn.linter.oasys.user.service.RoleService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final PermissionService permissionService;

    public RoleServiceImpl(RoleDao roleDao, PermissionService permissionService) {
        this.roleDao = roleDao;
        this.permissionService = permissionService;
    }

    @Override
    public Role queryById(Integer id) {
        return roleDao.selectById(id);
    }

    @Override
    public List<Role> list() {
        return roleDao.list();
    }

    @Override
    public List<Role> listByUsername(String username) {
        return roleDao.listByUsername(username);
    }

    @Override
    public Role create(Role role) {
        LocalDateTime now = LocalDateTime.now();
        role.setCreateTime(now);
        role.setUpdateTime(now);
        roleDao.insert(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        roleDao.update(role);
        return queryById(role.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return roleDao.delete(id) > 0;
    }

    @Override
    public List<Permission> listPermission(Integer id, boolean treeMode) {
        return permissionService.listByRoleId(id, treeMode);
    }

    @CacheEvict(value = "permission-role", allEntries = true)
    @Override
    public void createPermission(Integer id, List<Permission> permissions) {
        if (!permissions.isEmpty()) {
            roleDao.insertPermission(id, permissions);
        }
    }

    @CacheEvict(value = "permission-role", allEntries = true)
    @Override
    public void updatePermission(Integer id, List<Permission> permissions) {
        if (!permissions.isEmpty()) {
            roleDao.deletePermission(id);
            roleDao.insertPermission(id, permissions);
        }
    }

}