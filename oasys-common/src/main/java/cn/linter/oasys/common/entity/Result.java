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
public class Result<T> {

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
     * @param data   数据
     * @param <T>    类型
     * @return 响应
     */
    public static <T> Result<T> sendSuccess(Integer code, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        return result;
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
    public static <T> Result<T> sendSuccess(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
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
    public static <T> Result<T> sendSuccess(Integer code, T data, Long size) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setSize(size);
        return result;
    }

    /**
     * 返回失败响应
     *
     * @param code  状态码
     * @param message 信息
     * @param <T>     类型
     * @return 响应
     */
    public static <T> Result<T> sendError(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}