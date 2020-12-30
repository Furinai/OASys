package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.user.entity.Role;
import cn.linter.oasys.user.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{id}")
    public Result<Role> queryRole(@PathVariable("id") Integer id) {
        return Result.of(ResultStatus.SUCCESS, roleService.queryById(id));
    }

    @GetMapping
    public Result<Page<Role>> listRole(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Role> pageInfo = roleService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Role> createRole(@RequestBody @Validated({Role.Create.class}) Role role) {
        return Result.of(ResultStatus.SUCCESS, roleService.create(role));
    }

    @PutMapping
    public Result<Role> updateRole(@RequestBody @Validated({Role.Update.class}) Role role) {
        return Result.of(ResultStatus.SUCCESS, roleService.update(role));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteRole(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return ResultStatus.SUCCESS;
    }

}