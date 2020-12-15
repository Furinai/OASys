package cn.linter.oasys.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 响应实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Data
@ToString
@EqualsAndHashCode
public class Response<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
    /**
     * 数据总数
     */
    private Long size;

    /**
     * 返回成功响应
     *
     * @param code 状态码
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer code) {
        Response<T> response = new Response<>();
        response.setCode(code);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param code 状态码
     * @param data   数据
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer code, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param code  状态码
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param code  状态码
     * @param message 信息
     * @param data    数据
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer code, String message, T data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    /**
     * 返回成功响应
     *
     * @param code 状态码
     * @param data   数据
     * @param size   数据总数
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Response<T> sendSuccess(Integer code, T data, Long size) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setData(data);
        response.setSize(size);
        return response;
    }

    /**
     * 返回失败响应
     *
     * @param code  状态码
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Response<T> sendError(Integer code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}