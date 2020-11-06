package cn.linter.oasys.user.service.impl;


import cn.linter.oasys.common.entity.Role;
import cn.linter.oasys.user.dao.RoleDao;
import cn.linter.oasys.user.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 单个角色
     */
    @Override
    public Role getById(Long id) {
        return roleDao.selectById(id);
    }

    /**
     * 分页查询所有角色
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 角色列表
     */
    @Override
    public PageInfo<Role> getAll(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(roleDao.selectAll());
    }

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 角色
     */
    @Override
    public Role add(Role role) {
        roleDao.insert(role);
        return role;
    }

    /**
     * 更新角色
     *
     * @param role 角色
     * @return 角色
     */
    @Override
    public Role update(Role role) {
        roleDao.update(role);
        return getById(role.getId());
    }

    /**
     * 通过角色ID删除角色
     *
     * @param id 角色ID
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return roleDao.deleteById(id) > 0;
    }

}