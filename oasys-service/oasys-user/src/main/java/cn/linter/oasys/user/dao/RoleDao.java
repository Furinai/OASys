package cn.linter.oasys.user.dao;

import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Mapper
public interface RoleDao {

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 单个角色
     */
    Role selectById(Integer id);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> list();

    /**
     * 通过角色实例查询所有角色
     *
     * @param role 角色
     * @return 角色列表
     */
    List<Role> listByEntity(Role role);

    /**
     * 通过用户名查询用户所有角色
     *
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> listByUsername(String username);

    /**
     * 新增角色
     *
     * @param role 角色实例
     * @return 角色实例
     */
    int insert(Role role);

    /**
     * 更新角色
     *
     * @param role 角色实例
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return 影响行数
     */
    int delete(Integer id);

    /**
     * 新增角色的权限
     *
     * @param id          角色ID
     * @param permissions 权限列表
     */
    void insertPermission(@Param("roleId") Integer id, @Param("permissions") List<Permission> permissions);

    /**
     * 删除用户的角色
     *
     * @param id 用户ID
     */
    void deletePermission(@Param("roleId") Integer id);

}