package cn.linter.oasys.user.service;

import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public interface UserService {

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    User query(String username);

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    PageInfo<User> list(int pageNumber, int pageSize);

    /**
     * 通过用户名查询所有角色
     *
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> listRoleByUsername(String username);

    /**
     * 查询用户的权限
     *
     * @param username 用户名
     * @param treeMode 是否树形
     * @return 权限列表
     */
    List<Permission> listPermissionByUsername(String username, boolean treeMode);

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User create(User user);

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User update(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    boolean delete(Long id);

}