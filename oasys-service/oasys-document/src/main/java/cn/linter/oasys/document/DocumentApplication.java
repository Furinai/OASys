package cn.linter.oasys.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxiaoyang
 * @since 2020/11/06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentApplication.class, args);
    }

}
