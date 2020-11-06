package cn.linter.oasys.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@ApiModel("角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 719603973978603507L;
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long id;
    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}