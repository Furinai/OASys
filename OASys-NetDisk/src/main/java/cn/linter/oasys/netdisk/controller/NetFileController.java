package cn.linter.oasys.netdisk.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.netdisk.entity.NetFile;
import cn.linter.oasys.netdisk.service.NetFileService;
import com.github.pagehelper.PageInfo;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 文件控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Api(tags = "文件控制器")
@RestController
public class NetFileController {

    @Autowired
    private NetFileService netFileService;

    @ApiOperation("分页查询所有文件")
    @GetMapping("net-files")
    public Response<List<NetFile>> listNetFile(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                               @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize,
                                               @ApiParam("文件实例") NetFile netFile) {
        PageInfo<NetFile> pageInfo = netFileService.list(pageNumber, pageSize, netFile);
        return Response.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    @ApiOperation("创建文件夹")
    @PostMapping("net-folder")
    public Response<NetFile> createNetFolder(@RequestBody @ApiParam("文件夹实例") NetFile netFile) {
        return Response.sendSuccess(201, netFileService.createNetFolder(netFile));
    }

    @ApiOperation("上传文件")
    @PostMapping("net-file")
    public Response<NetFile> uploadNetFile(@ApiParam("二进制文件") MultipartFile multipartFile, @ApiParam("文件") NetFile netFile) throws Exception {
        return Response.sendSuccess(201, netFileService.uploadNetFile(multipartFile, netFile));
    }

    @ApiOperation("下载文件")
    @GetMapping("net-file/{id}")
    public Response<?> downloadNetFile(@PathVariable("id") @ApiParam("文件ID") Long id, HttpServletResponse response) throws ServerException, InsufficientDataException,
            NoSuchAlgorithmException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException, ErrorResponseException {
        try (InputStream in = netFileService.downloadNetFile(response, id);
             OutputStream out = response.getOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.sendSuccess(200);
    }

    @ApiOperation("更新文件")
    @PutMapping("net-file")
    public Response<?> updateNetFile(@RequestBody @ApiParam("文件实例") NetFile netFile) {
        if (netFileService.update(netFile)) {
            return Response.sendSuccess(200);
        }
        return Response.sendError(404, "文件不存在！");
    }

    @ApiOperation("删除文件")
    @DeleteMapping("net-file/{id}")
    public Response<?> deleteNetFile(@PathVariable("id") @ApiParam("文件ID") Long id) throws IOException, InvalidResponseException, InvalidKeyException,
            NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        if (netFileService.delete(id)) {
            return Response.sendSuccess(200);
        }
        return Response.sendError(404, "文件不存在！");
    }

}