package cn.linter.oasys.information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationApplication.class, args);
    }

}
