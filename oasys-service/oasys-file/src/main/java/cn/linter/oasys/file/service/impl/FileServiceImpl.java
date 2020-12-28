package cn.linter.oasys.file.service.impl;

import cn.linter.oasys.file.dao.FileDao;
import cn.linter.oasys.file.entity.File;
import cn.linter.oasys.file.service.FileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.*;
import io.minio.errors.*;
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
import java.util.stream.Collectors;

/**
 * 文件服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;
    private final MinioClient minioClient;

    @Value("${minio.file-bucket-name}")
    private String bucketName;

    public FileServiceImpl(FileDao fileDao, MinioClient minioClient) {
        this.fileDao = fileDao;
        this.minioClient = minioClient;
    }

    @Override
    public PageInfo<File> listByEntity(int pageNumber, int pageSize, File file) {
        PageHelper.startPage(pageNumber, pageSize);
        List<File> files = fileDao.listByEntity(file);
        //列表排序，文件夹在前，文件在后
        return PageInfo.of(files.parallelStream()
                .sorted((a, b) -> a.isFolder() == b.isFolder() ? 0 : a.isFolder() ? -1 : 1)
                .collect(Collectors.toList())
        );
    }

    @Override
    public File create(MultipartFile multipartFile, File file) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        if (multipartFile == null) {
            file.setType("文件夹");
            file.setSize("-");
        } else {
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
            file.setType(fileName.substring(dotIndex + 1));
            file.setSize(formatSize(multipartFile.getSize()));
            file.setContentType(multipartFile.getContentType());
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(filePath)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType()).build());
        }
        file.setShared(false);
        file.setCreateTime(LocalDateTime.now());
        file.setUpdateTime(LocalDateTime.now());
        fileDao.insert(file);
        return file;
    }

    @Override
    public InputStream getById(Long id, HttpServletResponse response) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = fileDao.selectById(id);
        if (file == null) {
            return null;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", file.getContentType());
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        response.setHeader("Content-Disposition", "attachment;" + "filename=" + fileName.replace("+", "%20"));
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(file.getPath()).build());
    }

    @Override
    public File update(File file) {
        //todo 处理非共享文件夹下的共享文件
        file.setUpdateTime(LocalDateTime.now());
        fileDao.update(file);
        return fileDao.selectById(file.getId());
    }

    @Override
    public void deleteById(Long id) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = fileDao.selectById(id);
        //todo 处理删除非空文件夹
        if (file != null && !file.isFolder()) {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(file.getPath()).build());
        }
        fileDao.deleteById(id);
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