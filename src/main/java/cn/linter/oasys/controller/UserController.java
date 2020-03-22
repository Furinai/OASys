package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Response getAuthentication(@AuthenticationPrincipal User user) {
        user.setPassword(null);
        return new Response("success", user);
    }

    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user.getId() == 0) {
            return new Response("error", "此用户不存在！");
        }
        return new Response("success", user);
    }

    @PreAuthorize("hasRole('经理')")
    @GetMapping("/getUsers")
    public Response getUsers(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                             @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        PageInfo<?> pageInfo = userService.getUsers(pageNumber, pageSize);
        return new Response("success", pageInfo.getTotal(), pageInfo.getList());
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if (result == -1) {
            return new Response("error", "用户名已存在！");
        }
        return new Response("success", "更新成功！");
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/addUser")
    public Response addUser(@RequestBody User user) {
        int result = userService.addUser(user);
        if (result == -1) {
            return new Response("error", "用户名已存在！");
        }
        return new Response("success", "添加成功！");
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/deleteUser")
    public Response deleteUser(@RequestBody Integer[] ids) {
        userService.deleteUser(ids);
        return new Response("success", "删除成功！");
    }
}
