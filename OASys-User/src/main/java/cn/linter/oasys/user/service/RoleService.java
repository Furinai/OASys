package cn.linter.oasys.user.service;

import cn.linter.oasys.user.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * 角色服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public interface RoleService {

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 单个角色
     */
    Role getById(Long id);

    /**
     * 分页查询所有角色
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 角色列表
     */
    PageInfo<Role> getAll(int pageNumber, int pageSize);

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 角色
     */
    Role add(Role role);

    /**
     * 更新角色
     *
     * @param role 角色
     * @return 角色
     */
    Role update(Role role);

    /**
     * 通过角色ID删除角色
     *
     * @param id 角色ID
     * @return 是否成功
     */
    boolean deleteById(Long id);

}