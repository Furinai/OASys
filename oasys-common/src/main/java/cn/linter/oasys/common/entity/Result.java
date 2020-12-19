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
    private String code;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 返回响应
     *
     * @param status 状态
     * @param data   数据
     * @return 响应
     */
    public static <T> Result<T> of(ResultStatus status, T data) {
        Result<T> result = new Result<>();
        result.setCode(status.getCode());
        result.setMessage(status.getMessage());
        result.setData(data);
        return result;
    }

}