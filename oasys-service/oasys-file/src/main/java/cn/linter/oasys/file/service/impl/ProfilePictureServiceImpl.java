package cn.linter.oasys.file.service.impl;

import cn.linter.oasys.file.service.ProfilePictureService;
import io.minio.*;
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

    private final MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.profile-picture-bucket-name}")
    private String bucketName;

    public ProfilePictureServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String createProfilePicture(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            String policy = "{\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]}," +
                    "{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}],\"Version\":\"2012-10-17\"}";
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        }
        //todo 头像存储在用户各自的目录中
        String filePath = multipartFile.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(filePath)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType()).build());
        return endPoint + "/" + bucketName + "/" + filePath;
    }

}
