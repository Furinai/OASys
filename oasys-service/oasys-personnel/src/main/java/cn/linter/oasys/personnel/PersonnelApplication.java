package cn.linter.oasys.personnel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PersonnelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelApplication.class, args);
    }

}
