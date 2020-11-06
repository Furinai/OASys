package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.common.entity.Role;
import cn.linter.oasys.user.service.RoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Api(tags = "角色控制器")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 通过ID查询单个角色
     *
     * @param id 角色ID
     * @return 单个角色
     */
    @ApiOperation("通过ID查询单个角色")
    @GetMapping("getRoleById")
    public Response<Role> getRoleById(@ApiParam("角色ID") Long id) {
        return Response.sendSuccess(roleService.getById(id));
    }

    /**
     * 分页查询所有角色
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 角色列表
     */
    @ApiOperation("分页查询所有角色")
    @GetMapping("getAllRole")
    public Response<List<Role>> getAllRole(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                           @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<Role> pageInfo = roleService.getAll(pageNumber, pageSize);
        return Response.sendSuccess(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增角色
     *
     * @param role 角色
     * @return 角色
     */
    @ApiOperation("新增角色")
    @PostMapping("addRole")
    public Response<Role> addRole(@RequestBody @ApiParam("角色") Role role) {
        return Response.sendSuccess(roleService.add(role));
    }

    /**
     * 更新角色
     *
     * @param role 角色
     * @return 角色
     */
    @ApiOperation("更新角色")
    @PutMapping("updateRole")
    public Response<Role> updateRole(@RequestBody @ApiParam("角色") Role role) {
        return Response.sendSuccess(roleService.update(role));
    }

    /**
     * 通过角色ID删除角色
     *
     * @param id 角色ID
     * @return 是否成功
     */
    @ApiOperation("通过ID删除角色")
    @DeleteMapping("deleteRole")
    public Response<Boolean> deleteRole(@ApiParam("角色ID") Long id) {
        return Response.sendSuccess(roleService.deleteById(id));
    }

}