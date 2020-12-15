package cn.linter.oasys.auth.controller;

import cn.linter.oasys.auth.entity.User;
import cn.linter.oasys.auth.service.UserService;
import cn.linter.oasys.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    @GetMapping("user/{id}")
    public Response<User> queryUser(@PathVariable("id") Long id) {
        User user = userService.query(id);
        if (user != null) {
            return Response.sendSuccess(200, user);
        }
        return Response.sendError(404, "用户不存在");
    }

}