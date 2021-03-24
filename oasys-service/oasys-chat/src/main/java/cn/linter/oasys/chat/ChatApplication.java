package cn.linter.oasys.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 聊天服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

}
