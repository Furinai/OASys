package cn.linter.oasys.personnel.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.personnel.entity.User;
import cn.linter.oasys.personnel.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("user/{id}")
    public Result<User> queryUser(@PathVariable("id") @ApiParam("用户ID") Long id) {
        User user = userService.query(id);
        return Result.of(ResultStatus.SUCCESS, user);
    }

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    @ApiOperation("分页查询所有用户")
    @GetMapping("users")
    public Result<Page<User>> listUser(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                       @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<User> pageInfo = userService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    @ApiOperation("新增用户")
    @PostMapping("user")
    public Result<User> createUser(@RequestBody @ApiParam("用户") User user) {
        return Result.of(ResultStatus.SUCCESS, userService.create(user));
    }

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    @ApiOperation("更新用户")
    @PutMapping("user")
    public Result<User> updateUser(@RequestBody @ApiParam("用户") User user) {
        User updatedUser = userService.update(user);
        return Result.of(ResultStatus.SUCCESS, updatedUser);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @ApiOperation("通过ID删除用户")
    @DeleteMapping("user/{id}")
    public ResultStatus deleteUser(@PathVariable("id") @ApiParam("用户ID") Long id) {
        userService.delete(id);
        return ResultStatus.SUCCESS;
    }

}