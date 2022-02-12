package cn.linter.oasys.gateway.entity;

import lombok.Data;
import org.springframework.http.HttpMethod;

/**
 * 权限实体类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@Data
public class Permission {

    /**
     * 资源路径
     */
    private String resourcePath;
    /**
     * 请求方法
     */
    private HttpMethod requestMethod;

}