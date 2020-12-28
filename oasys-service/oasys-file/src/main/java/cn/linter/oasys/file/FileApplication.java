package cn.linter.oasys.file;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 文件服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/06
 */
@SpringCloudApplication
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

}
