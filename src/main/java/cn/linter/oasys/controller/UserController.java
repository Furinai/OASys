package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public Response<User> getAuthentication(@AuthenticationPrincipal User user) {
        user.setPassword(null);
        return Response.success("获取成功！", user);
    }

    @GetMapping("/user/{id}")
    public Response<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (user.getId() == 0) {
            return Response.error("此用户不存在！");
        }
        return Response.success("获取成功！", user);
    }

    @PreAuthorize("hasRole('经理')")
    @GetMapping("/getUsers")
    public Response<PageInfo<User>> getUsers(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                             @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        PageInfo<User> pageInfo = userService.getUsers(pageNumber, pageSize);
        return Response.success("获取成功！", pageInfo);
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/updateUser")
    public Response<?> updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if (result == -1) {
            return Response.error("用户名已存在！");
        }
        return Response.success("更新成功！");
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/addUser")
    public Response<?> addUser(@RequestBody User user) {
        int result = userService.addUser(user);
        if (result == -1) {
            return Response.error("用户名已存在！");
        }
        return Response.success("添加成功！");
    }

    @PreAuthorize("hasRole('经理')")
    @PostMapping("/deleteUser")
    public Response<?> deleteUser(@RequestBody Integer[] ids) {
        userService.deleteUser(ids);
        return Response.success("删除成功！");
    }
}
