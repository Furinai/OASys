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
     * 状态码
     */
    @ApiModelProperty("状态码")
    private Integer status;
    /**
     * 状态信息
     */
    @ApiModelProperty("状态信息")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;
    /**
     * 数据总数
     */
    @ApiModelProperty("数据总数")
    private Long size;

    /**
     * 返回成功响应
     *
     * @param status 状态码
     * @param data   数据
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer status, T data) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param status 状态码
     * @param data   数据
     * @param size   数据总数
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer status, T data, Long size) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setData(data);
        response.setSize(size);
        return response;
    }

    /**
     * 返回失败响应
     *
     * @param status  状态码
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendError(Integer status, String message) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
