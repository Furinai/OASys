package cn.linter.oasys.gateway.entity;

import org.springframework.http.HttpMethod;

import java.io.Serializable;

/**
 * 权限实体类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 799092423622491007L;
    /**
     * 资源路径
     */
    private String resourcePath;
    /**
     * 请求方法
     */
    private HttpMethod requestMethod;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(HttpMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

}