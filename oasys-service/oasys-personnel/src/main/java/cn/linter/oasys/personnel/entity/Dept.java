package cn.linter.oasys.personnel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 部门实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
@ApiModel("部门")
@Data
@ToString
@EqualsAndHashCode
public class Dept implements Serializable {

    private static final long serialVersionUID = 504314419569182280L;
    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;
    /**
     * 部门名
     */
    @ApiModelProperty("部门名")
    private String name;

}