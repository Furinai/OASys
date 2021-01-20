package cn.linter.oasys.user.service;

import cn.linter.oasys.user.entity.Permission;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
public interface PermissionService {

    /**
     * 通过ID查询单个权限
     *
     * @param id 权限ID
     * @return 单个权限
     */
    Permission queryById(Integer id);

    /**
     * 查询所有权限
     *
     * @param treeMode 是否树形
     * @return 权限列表
     */
    List<Permission> list(boolean treeMode);

    /**
     * 通过用户ID查询所有权限
     *
     * @param userId   用户ID
     * @param treeMode 是否树形
     * @return 权限列表
     */
    List<Permission> listByUserId(Integer userId, Boolean treeMode);

    /**
     * 通过角色ID查询所有权限
     *
     * @param roleId   角色ID
     * @param treeMode 是否树形
     * @return 权限列表
     */
    List<Permission> listByRoleId(Integer roleId, boolean treeMode);

    /**
     * 新增权限
     *
     * @param permission 权限实例
     * @return 权限实例
     */
    Permission create(Permission permission);

    /**
     * 更新权限
     *
     * @param permission 权限实例
     * @return 权限实例
     */
    Permission update(Permission permission);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return 是否成功
     */
    boolean delete(Integer id);

    /**
     * 转换列表结构到树形结构
     *
     * @param permissions 列表结构权限集合
     * @return 树形结构权限集合
     */
    List<Permission> convertListToTree(List<Permission> permissions);

    /**
     * 转换树形结构到列表结构
     *
     * @param permissions 树形结构权限集合
     * @return 列表结构权限集合
     */
    List<Permission> convertTreeToList(List<Permission> permissions);

}