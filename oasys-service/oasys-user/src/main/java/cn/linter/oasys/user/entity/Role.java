package cn.linter.oasys.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色实体类
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@Data
@ToString
@EqualsAndHashCode
public class Role implements Serializable {

    private static final long serialVersionUID = -44062214609899076L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;

}