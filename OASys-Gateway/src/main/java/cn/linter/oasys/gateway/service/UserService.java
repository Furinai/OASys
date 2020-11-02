package cn.linter.oasys.gateway.service;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.common.entity.Role;
import cn.linter.oasys.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户服务FeignClient
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@FeignClient(value = "user")
public interface UserService {

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    @ResponseBody
    @GetMapping("getUserByUsername")
    Response<User> getUserByUsername(@RequestParam("username") String username);

    /**
     * 通过用户ID查询所有角色
     *
     * @param userId 用户ID
     * @return 用户列表
     */
    @ResponseBody
    @GetMapping("getAllRoleOfUser")
    Response<List<Role>> getAllRoleOfUser(@RequestParam("userId") Long userId);

}
