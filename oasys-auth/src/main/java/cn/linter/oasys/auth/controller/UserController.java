package cn.linter.oasys.auth.controller;

import cn.linter.oasys.auth.entity.User;
import cn.linter.oasys.auth.service.UserService;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping("user")
    public Result<User> queryUser(@RequestHeader("Authorization") String token) {
        User user = userService.queryById(JwtUtil.getUserId(token));
        if (user != null) {
            return Result.sendSuccess(200, user);
        }
        return Result.sendError(404, "用户不存在");
    }

}