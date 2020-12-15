package cn.linter.oasys.document.service.impl;

import cn.linter.oasys.document.dao.FileDao;
import cn.linter.oasys.document.entity.File;
import cn.linter.oasys.document.service.FileService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * 文件服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.file-bucket-name}")
    private String bucketName;

    @Override
    public PageInfo<File> list(int pageNumber, int pageSize, File file) {
        PageHelper.startPage(pageNumber, pageSize);
        List<File> files = fileDao.list(file);
        //列表排序，文件夹在前，文件在后
        files.sort((a, b) -> {
            if (a.isFolder() == b.isFolder()) {
                return 0;
            }
            return a.isFolder() ? -1 : 1;
        });
        return PageInfo.of(files);
    }

    @Override
    public File createFolder(File file) {
        file.setType("文件夹");
        file.setSize("-");
        file.setShared(false);
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        fileDao.insert(file);
        return file;
    }

    @Override
    public File uploadFile(MultipartFile multipartFile, File file) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        String fileName = multipartFile.getOriginalFilename();
        int dotIndex = Objects.requireNonNull(fileName).lastIndexOf('.');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/");
        String filePath = file.getUserId() + LocalDate.now().format(formatter) + fileName;
        file.setName(fileName);
        file.setPath(filePath);
        file.setShared(false);
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        file.setType(fileName.substring(dotIndex + 1));
        file.setSize(formatSize(multipartFile.getSize()));
        file.setContentType(multipartFile.getContentType());
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(filePath)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType()).build());
        fileDao.insert(file);
        return file;
    }

    @Override
    public InputStream downloadFile(Long id, HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = fileDao.select(id);
        if (file == null) {
            return null;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", file.getContentType());
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;" + "filename=" + fileName.replace("+", "%20"));
        MinioClient minioClient = MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(file.getPath()).build());
    }

    @Override
    public File update(File file) {
        //todo 处理非共享文件夹下的共享文件
        file.setUpdateTime(LocalDateTime.now());
        if (fileDao.update(file) > 0) {
            return fileDao.select(file.getId());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = fileDao.select(id);
        if (file == null) {
            return false;
        }
        //todo 处理删除非空文件夹
        if (!file.isFolder()) {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(file.getPath()).build());
        }
        return fileDao.delete(id) > 0;
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