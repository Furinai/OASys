package cn.linter.oasys.document.service.impl;

import cn.linter.oasys.document.service.ProfilePictureService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 头像服务实现类
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.profile-picture-bucket-name}")
    private String bucketName;

    @Override
    public String uploadProfilePicture(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = MinioClient.builder().endpoint(endPoint).credentials(accessKey, secretKey).build();
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            //todo 设置访问策略
        }
        //todo 头像存储在用户各自的目录中
        String filePath = multipartFile.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(filePath)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType()).build());
        return endPoint + "/" + bucketName + "/" + filePath;
    }

}
