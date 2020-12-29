package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.entity.User;
import cn.linter.oasys.user.service.RoleService;
import cn.linter.oasys.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("{username}")
    public Result<User> queryUser(@PathVariable("username") String username) {
        User user = userService.query(username);
        return Result.of(ResultStatus.SUCCESS, user);
    }

    @GetMapping("{username}/roles")
    public Result<List<Role>> queryRoleOfUser(@PathVariable("username") String username) {
        List<Role> roles = roleService.listByUsername(username);
        return Result.of(ResultStatus.SUCCESS, roles);
    }

    @GetMapping
    public Result<Page<User>> listUser(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<User> pageInfo = userService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<User> createUser(@RequestBody User user) {
        return Result.of(ResultStatus.SUCCESS, userService.create(user));
    }

    @PutMapping
    public Result<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return Result.of(ResultStatus.SUCCESS, updatedUser);
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResultStatus.SUCCESS;
    }

}