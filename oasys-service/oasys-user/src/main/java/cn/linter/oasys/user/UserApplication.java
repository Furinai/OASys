package cn.linter.oasys.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 用户服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@SpringCloudApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
