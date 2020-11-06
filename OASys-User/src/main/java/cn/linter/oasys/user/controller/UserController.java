package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.common.entity.User;
import cn.linter.oasys.user.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Api(tags = "用户控制器")
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
    @ApiOperation("通过ID查询单个用户")
    @GetMapping("getUserById")
    public Response<User> getUserById(@ApiParam("用户ID") Long id) {
        return Response.sendSuccess(userService.getById(id));
    }

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    @ApiOperation("通过用户名查询单个用户")
    @GetMapping("getUserByUsername")
    public Response<User> getUserByUsername(@ApiParam("用户名") String username) {
        return Response.sendSuccess(userService.getByUsername(username));
    }

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    @ApiOperation("分页查询所有用户")
    @GetMapping("getAllUser")
    public Response<List<User>> getAllUser(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                           @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<User> pageInfo = userService.getAll(pageNumber, pageSize);
        return Response.sendSuccess(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户
     */
    @ApiOperation("新增用户")
    @PostMapping("addUser")
    public Response<User> addUser(@RequestBody @ApiParam("用户") User user) {
        return Response.sendSuccess(userService.add(user));
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 用户
     */
    @ApiOperation("更新用户")
    @PutMapping("updateUser")
    public Response<User> updateUser(@RequestBody @ApiParam("用户") User user) {
        return Response.sendSuccess(userService.update(user));
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @ApiOperation("通过ID删除用户")
    @DeleteMapping("deleteUser")
    public Response<Boolean> deleteUser(@ApiParam("用户ID") Long id) {
        return Response.sendSuccess(userService.deleteById(id));
    }

}