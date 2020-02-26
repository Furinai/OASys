package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUserById(@Param("id") int id);

    User selectUserByUsername(@Param("username") String username);

    List<User> selectUsers();

    int updateUser(User user);

    int insertUser(User user);

    void deleteUser(@Param("ids") Integer[] ids);
}