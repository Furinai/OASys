package cn.linter.oasys.chatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ChattingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChattingApplication.class, args);
    }

}
