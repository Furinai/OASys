package cn.linter.oasys.netdisk.service;

import cn.linter.oasys.netdisk.entity.NetFile;
import com.github.pagehelper.PageInfo;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
public interface NetFileService {

    /**
     * 分页查询所有文件
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @param netFile    文件实例
     * @return 文件列表
     */
    PageInfo<NetFile> list(int pageNumber, int pageSize, NetFile netFile);

    /**
     * 创建文件夹
     *
     * @param netFile 文件夹实例
     * @return 文件夹实例
     */
    NetFile createNetFolder(NetFile netFile);

    /**
     * 上传文件
     *
     * @param multipartFile 二进制文件
     * @param netFile       文件实例
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
    NetFile uploadNetFile(MultipartFile multipartFile, NetFile netFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    /**
     * 下载文件
     *
     * @param response Http响应
     * @param id       文件ID
     * @return InputStream
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
    InputStream downloadNetFile(HttpServletResponse response, Long id) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    /**
     * 更新文件
     *
     * @param netFile 文件实例
     * @return 是否成功
     */
    boolean update(NetFile netFile);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 是否成功
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
    boolean delete(Long id) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}