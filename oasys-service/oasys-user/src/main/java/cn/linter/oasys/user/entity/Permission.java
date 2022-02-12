package cn.linter.oasys.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限实体类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@Data
@ToString
@EqualsAndHashCode
public class Permission {

    /**
     * 主键ID
     */
    @NotNull(message = "权限ID不能为空", groups = {Update.class})
    private Integer id;
    /**
     * 权限名
     */
    @NotBlank(message = "权限名不能为空", groups = {Create.class})
    @Length(min = 2, max = 20, message = "权限名长度为 2 到 20 之间", groups = {Create.class, Update.class})
    private String name;
    /**
     * 类型
     */
    @NotNull(message = "权限类型不能为空", groups = {Create.class})
    private Type type;
    /**
     * 图标
     */
    @Length(min = 2, max = 40, message = "图标样式长度为 2 到 40 之间", groups = {Create.class, Update.class})
    private String icon;
    /**
     * 父级ID
     */
    @NotNull(message = "父级ID不能为空", groups = {Create.class})
    private Integer parentId;
    /**
     * 资源路径
     */
    @Length(min = 2, max = 40, message = "资源路径长度为 2 到 40 之间", groups = {Create.class, Update.class})
    private String resourcePath;
    /**
     * 请求方法
     */
    private HttpMethod requestMethod;
    /**
     * 路由名称
     */
    @Length(min = 2, max = 40, message = "路由名称长度为 2 到 40 之间", groups = {Create.class, Update.class})
    private String routerName;
    /**
     * 路由路径
     */
    @Length(min = 2, max = 40, message = "路由路径长度为 2 到 40 之间", groups = {Create.class, Update.class})
    private String routerPath;
    /**
     * 组件路径
     */
    @Length(min = 2, max = 40, message = "组件路径长度为 2 到 40 之间", groups = {Create.class, Update.class})
    private String componentPath;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**
     * 子集合
     */
    private List<Permission> children;

    /**
     * 权限类型
     */
    public enum Type {
        /**
         * 分类
         */
        category,
        /**
         * 菜单
         */
        menu,
        /**
         * 资源
         */
        resource
    }

    public interface Create {
    }

    public interface Update {
    }

}