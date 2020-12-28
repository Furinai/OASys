package cn.linter.oasys.file.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.file.entity.File;
import cn.linter.oasys.file.service.FileService;
import com.github.pagehelper.PageInfo;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public Result<Page<File>> listFile(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                       File file) {
        PageInfo<File> pageInfo = fileService.listByEntity(pageNumber, pageSize, file);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<File> createFile(@RequestParam(required = false) MultipartFile multipartFile, File file) throws Exception {
        return Result.of(ResultStatus.SUCCESS, fileService.create(multipartFile, file));
    }

    @GetMapping("{id}")
    public void downloadFile(@PathVariable("id") Long id, HttpServletResponse response) throws ServerException, InsufficientDataException,
            NoSuchAlgorithmException, InternalException, InvalidResponseException, XmlParserException, InvalidKeyException, ErrorResponseException {
        try (InputStream in = fileService.getById(id, response); OutputStream out = response.getOutputStream()) {
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping
    public Result<File> updateFile(@RequestBody File file) {
        //todo 验证是否为自己的文件
        File updatedFile = fileService.update(file);
        return Result.of(ResultStatus.SUCCESS, updatedFile);
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteFile(@PathVariable("id") Long id) throws IOException, InvalidResponseException, InvalidKeyException,
            NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        fileService.deleteById(id);
        return ResultStatus.SUCCESS;
    }

}