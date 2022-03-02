package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.user.dto.PermissionRoleDTO;
import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.service.PermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限控制器
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@RestController
@RequestMapping("permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public Result<List<Permission>> listPermission(@RequestParam(defaultValue = "false") boolean treeMode) {
        return Result.of(ResultStatus.SUCCESS, permissionService.list(treeMode));
    }

    @GetMapping("roles")
    public List<PermissionRoleDTO> listRole() {
        return permissionService.listRoleByType(Permission.Type.resource);
    }

    @PostMapping
    public Result<Permission> createPermission(@RequestBody @Validated({Permission.Create.class}) Permission permission) {
        //todo 分类型参数校验
        return Result.of(ResultStatus.SUCCESS, permissionService.create(permission));
    }

    @PutMapping
    public Result<Permission> updatePermission(@RequestBody @Validated({Permission.Update.class}) Permission permission) {
        return Result.of(ResultStatus.SUCCESS, permissionService.update(permission));
    }

    @DeleteMapping("{id}")
    public ResultStatus deletePermission(@PathVariable Integer id) {
        permissionService.delete(id);
        return ResultStatus.SUCCESS;
    }

}