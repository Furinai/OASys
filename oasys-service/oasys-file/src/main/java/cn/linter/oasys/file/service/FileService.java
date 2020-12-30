package cn.linter.oasys.file.service;

import cn.linter.oasys.file.entity.File;
import com.github.pagehelper.PageInfo;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
public interface FileService {

    /**
     * 通过文件实体分页查询所有文件
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @param file       文件实体
     * @return 文件列表
     */
    PageInfo<File> listByEntity(int pageNumber, int pageSize, File file);

    /**
     * 通过ID获取文件
     *
     * @param id       文件ID
     * @param response Http响应
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    void getById(Long id, HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    /**
     * 创建文件
     *
     * @param multipartFile 二进制文件
     * @param file          文件实例
     * @return 文件实例
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    File create(MultipartFile multipartFile, File file) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;


    /**
     * 更新文件
     *
     * @param file 文件实例
     * @return 文件实例
     */
    File update(File file);

    /**
     * 通过ID删除文件
     *
     * @param id 文件ID
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    void deleteById(Long id) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}