package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.user.dao.RoleDao;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.service.RoleService;
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

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
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

}