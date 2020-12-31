package cn.linter.oasys.file.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio配置
 *
 * @author wangxiaoyang
 * @date 2020/12/23
 */
@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClientFactory() {
        return MinioClient.builder().endpoint(endPoint)
                .credentials(accessKey, secretKey).build();
    }

}
