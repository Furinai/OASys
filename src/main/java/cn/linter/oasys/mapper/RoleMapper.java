package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    Role selectRoleById(@Param("id") int id);

    List<Role> selectRoles();
}
