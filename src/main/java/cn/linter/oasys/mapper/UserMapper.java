package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    User selectUserById(@Param("id") int id);

    User selectUserByUsername(@Param("username") String username);
}