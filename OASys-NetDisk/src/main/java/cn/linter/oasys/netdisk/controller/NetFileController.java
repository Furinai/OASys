package cn.linter.oasys.netdisk.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.netdisk.entity.NetFile;
import cn.linter.oasys.netdisk.service.NetFileService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/09
 */
@Api(tags = "文件控制器")
@RestController
public class NetFileController {

    @Autowired
    private NetFileService netFileService;

    /**
     * 通过ID查询单个文件
     *
     * @param id 文件ID
     * @return 单个文件
     */
    @ApiOperation("通过ID查询单个文件")
    @GetMapping("net-file/{id}")
    public Response<NetFile> queryNetFile(@PathVariable("id") @ApiParam("文件ID") Long id) {
        return Response.sendSuccess(200, netFileService.query(id));
    }

    /**
     * 分页查询所有文件
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 文件列表
     */
    @ApiOperation("分页查询所有文件")
    @GetMapping("net-files")
    public Response<List<NetFile>> listNetFile(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                               @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<NetFile> pageInfo = netFileService.list(pageNumber, pageSize);
        return Response.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增文件
     *
     * @param netFile 文件
     * @return 文件
     */
    @ApiOperation("新增文件")
    @PostMapping("net-file")
    public Response<NetFile> createNetFile(@RequestBody @ApiParam("文件") NetFile netFile) {
        return Response.sendSuccess(201, netFileService.create(netFile));
    }

    /**
     * 更新文件
     *
     * @param netFile 文件
     * @return 文件
     */
    @ApiOperation("更新文件")
    @PutMapping("net-file")
    public Response<NetFile> updateNetFile(@RequestBody @ApiParam("文件") NetFile netFile) {
        return Response.sendSuccess(200, netFileService.update(netFile));
    }

    /**
     * 通过文件ID删除文件
     *
     * @param id 文件ID
     * @return 是否成功
     */
    @ApiOperation("通过ID删除文件")
    @DeleteMapping("net-file")
    public Response<Boolean> deleteNetFile(@ApiParam("文件ID") Long id) {
        return Response.sendSuccess(200, netFileService.delete(id));
    }

}