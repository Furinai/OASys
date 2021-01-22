package cn.linter.oasys.gateway.entity;

import java.io.Serializable;

/**
 * 角色实体类
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -44062214609899076L;
    /**
     * 角色名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}