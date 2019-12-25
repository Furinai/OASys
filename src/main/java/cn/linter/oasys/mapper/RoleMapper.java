package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    void insertRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);

    int deleteRoleFromUser(@Param("userId") int userId, @Param("roleId") int roleId);

    List<Role> selectRolesByUserId(@Param("userId") int userId);
}
