package cn.linter.oasys.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @NotNull(message = "角色ID不能为空", groups = {Update.class})
    private Integer id;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空", groups = {Create.class})
    @Length(min = 2, max = 10, message = "角色名长度为 2 到 10 之间", groups = {Create.class, Update.class})
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public interface Create {
    }

    public interface Update {
    }

}