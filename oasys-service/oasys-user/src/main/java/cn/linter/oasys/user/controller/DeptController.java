package cn.linter.oasys.user.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.user.entity.Dept;
import cn.linter.oasys.user.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("dept/{id}")
    public Result<Dept> queryDept(@PathVariable("id") Integer id) {
        return Result.of(ResultStatus.SUCCESS, deptService.query(id));
    }

    @GetMapping("depts")
    public Result<Page<Dept>> listDept(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Dept> pageInfo = deptService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping("dept")
    public Result<Dept> createDept(@RequestBody Dept dept) {
        return Result.of(ResultStatus.SUCCESS, deptService.create(dept));
    }

    @PutMapping("dept")
    public Result<Dept> updateDept(@RequestBody Dept dept) {
        return Result.of(ResultStatus.SUCCESS, deptService.update(dept));
    }

    @DeleteMapping("dept/{id}")
    public Result<Boolean> deleteDept(@PathVariable("id") Integer id) {
        return Result.of(ResultStatus.SUCCESS, deptService.delete(id));
    }

}