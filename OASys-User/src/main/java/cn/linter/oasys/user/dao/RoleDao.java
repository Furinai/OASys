package cn.linter.oasys.user.dao;

import cn.linter.oasys.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Mapper
public interface RoleDao {

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 角色
     */
    Role selectById(Long id);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> selectAll();

    /**
     * 通过角色实体查询所有角色
     *
     * @param role 角色
     * @return 角色列表
     */
    List<Role> selectAllByEntity(Role role);

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 更新角色
     *
     * @param role 角色
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过角色ID删除角色
     *
     * @param id 角色ID
     * @return 影响行数
     */
    int deleteById(Long id);

}