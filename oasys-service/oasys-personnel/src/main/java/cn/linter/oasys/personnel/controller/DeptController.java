package cn.linter.oasys.personnel.controller;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.personnel.entity.Dept;
import cn.linter.oasys.personnel.service.DeptService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
@Api(tags = "部门控制器")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("查询单个部门")
    @GetMapping("dept/{id}")
    public Result<Dept> queryDept(@PathVariable("id") @ApiParam("部门ID") Integer id) {
        return Result.sendSuccess(200, deptService.query(id));
    }

    @ApiOperation("分页查询所有部门")
    @GetMapping("depts")
    public Result<List<Dept>> listDept(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                       @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<Dept> pageInfo = deptService.list(pageNumber, pageSize);
        return Result.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    @ApiOperation("新增部门")
    @PostMapping("dept")
    public Result<Dept> createDept(@RequestBody @ApiParam("部门实例") Dept dept) {
        return Result.sendSuccess(201, deptService.create(dept));
    }

    @ApiOperation("更新部门")
    @PutMapping("dept")
    public Result<Dept> updateDept(@RequestBody @ApiParam("部门实例") Dept dept) {
        return Result.sendSuccess(200, deptService.update(dept));
    }

    @ApiOperation("删除部门")
    @DeleteMapping("dept/{id}")
    public Result<Boolean> deleteDept(@PathVariable("id") @ApiParam("部门ID") Integer id) {
        return Result.sendSuccess(200, deptService.delete(id));
    }

}