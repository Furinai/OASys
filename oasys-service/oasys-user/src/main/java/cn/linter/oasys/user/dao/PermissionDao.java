package cn.linter.oasys.user.dao;

import cn.linter.oasys.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@Mapper
public interface PermissionDao {

    /**
     * 通过ID查询单个权限
     *
     * @param id 权限ID
     * @return 单个权限
     */
    Permission selectById(Integer id);

    /**
     * 查询所有权限
     *
     * @return 权限列表
     */
    List<Permission> list();

    /**
     * 通过角色ID查询所有权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<Permission> listByRoleId(Integer roleId);

    /**
     * 通过权限实例查询所有权限
     *
     * @param permission 权限
     * @return 权限列表
     */
    List<Permission> listByEntity(Permission permission);

    /**
     * 新增权限
     *
     * @param permission 权限实例
     * @return 权限实例
     */
    int insert(Permission permission);

    /**
     * 更新权限
     *
     * @param permission 权限实例
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return 影响行数
     */
    int delete(Integer id);

}