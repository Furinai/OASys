package cn.linter.oasys.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 考勤服务启动类
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@SpringCloudApplication
public class AttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
    }

}
