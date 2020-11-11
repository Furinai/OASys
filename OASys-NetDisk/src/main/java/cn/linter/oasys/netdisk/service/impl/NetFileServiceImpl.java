package cn.linter.oasys.netdisk.service.impl;

import cn.linter.oasys.netdisk.dao.NetFileDao;
import cn.linter.oasys.netdisk.entity.NetFile;
import cn.linter.oasys.netdisk.service.NetFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 文件服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Service
public class NetFileServiceImpl implements NetFileService {

    @Autowired
    private NetFileDao netFileDao;

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @Override
    public PageInfo<NetFile> list(int pageNumber, int pageSize, NetFile netFile) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(netFileDao.list(netFile));
    }

    @Override
    public NetFile createNetFolder(NetFile netFile) {
        netFile.setType("文件夹");
        netFileDao.insert(netFile);
        return netFile;
    }

    @Override
    public NetFile uploadNetFile(MultipartFile multipartFile, NetFile netFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
        String fileName = multipartFile.getOriginalFilename();
        int dotIndex = Objects.requireNonNull(fileName).lastIndexOf('.');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/");
        String date = LocalDate.now().format(formatter);
        String filePath = netFile.getUserId() + date + fileName;
        netFile.setPath(filePath);
        netFile.setName(fileName);
        netFile.setType(fileName.substring(dotIndex + 1));
        netFile.setSize(formatSize(multipartFile.getSize()));
        netFile.setContentType(multipartFile.getContentType());
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filePath)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType())
                .build());
        netFileDao.insert(netFile);
        return netFile;
    }

    @Override
    public InputStream downloadNetFile(HttpServletResponse response, Long id) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        NetFile netFile = netFileDao.select(id);
        String fileName = URLEncoder.encode(netFile.getName(), "UTF-8").replace("+", "%20");
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", netFile.getContentType());
        response.setHeader("Content-Disposition", "attachment;" + "filename=" + fileName);
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(netFile.getPath())
                .build());
    }

    @Override
    public boolean update(NetFile netFile) {
        return netFileDao.update(netFile) > 0;
    }

    @Override
    public boolean delete(Long id) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        NetFile netFile = netFileDao.select(id);
        if (netFile == null) {
            return false;
        }
        if (!"文件夹".equals(netFile.getType())) {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endPoint)
                    .credentials(accessKey, secretKey)
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(netFile.getPath())
                    .build());
        }
        return netFileDao.delete(id) > 0;
    }

    public String formatSize(long size) {
        if (size < 1024) {
            return size + "B";
        } else if (size < 1048576) {
            return (size >> 10) + "KB";
        } else {
            return (size >> 20) + "MB";
        }
    }

}