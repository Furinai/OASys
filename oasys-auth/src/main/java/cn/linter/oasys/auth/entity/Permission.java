package cn.linter.oasys.auth.entity;

import lombok.Data;

import java.util.List;

/**
 * 权限实体类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@Data
public class Permission {

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 权限名
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 路由名称
     */
    private String routerName;
    /**
     * 路由路径
     */
    private String routerPath;
    /**
     * 组件路径
     */
    private String componentPath;
    /**
     * 子集合
     */
    private List<Permission> children;

}