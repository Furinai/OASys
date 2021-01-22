package cn.linter.oasys.auth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1805200734827803764L;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String getRouterPath() {
        return routerPath;
    }

    public void setRouterPath(String routerPath) {
        this.routerPath = routerPath;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

}