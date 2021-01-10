package cn.linter.oasys.search;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 搜索服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@SpringCloudApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
