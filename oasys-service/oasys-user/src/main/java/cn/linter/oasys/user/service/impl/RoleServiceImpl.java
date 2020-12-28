package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.user.dao.RoleDao;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageInfo<Role> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(roleDao.list());
    }

    @Override
    public List<Role> listByUsername(String username) {
        return roleDao.listByUsername(username);
    }

    @Override
    public Role create(Role role) {
        roleDao.insert(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        roleDao.update(role);
        return queryById(role.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return roleDao.delete(id) > 0;
    }

}