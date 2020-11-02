package cn.linter.oasys.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@ApiModel("响应")
public class Response<T> {

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;
    /**
     * 信息
     */
    @ApiModelProperty("信息")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;
    /**
     * 列表元素数量
     */
    @ApiModelProperty("列表数量")
    private Long size;

    /**
     * 返回成功响应
     *
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(String message) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setMessage(message);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param data 数据
     * @param <T>  类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(T data) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setData(data);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param data 数据
     * @param size 列表元素数量
     * @param <T>  类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(T data, Long size) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setData(data);
        response.setSize(size);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param message 信息
     * @param data    数据
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(String message, T data) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param message 信息
     * @param data    数据
     * @param size    列表元素数量
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(String message, T data, Long size) {
        Response<T> response = new Response<>();
        response.setStatus("success");
        response.setMessage(message);
        response.setData(data);
        response.setSize(size);
        return response;
    }

    /**
     * 返回失败响应
     *
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendError(String message) {
        Response<T> response = new Response<>();
        response.setStatus("error");
        response.setMessage(message);
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
