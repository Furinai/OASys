package cn.linter.oasys.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
@Data
@ToString
@EqualsAndHashCode
public class Dept implements Serializable {

    private static final long serialVersionUID = 504314419569182280L;
    /**
     * 主键ID
     */
    @NotNull(message = "部门ID不能为空", groups = {Update.class})
    private Integer id;
    /**
     * 部门名
     */
    @NotBlank(message = "部门名不能为空", groups = {Create.class})
    @Length(min = 2, max = 10, message = "部门名长度为 2 到 10 之间", groups = {Create.class, Update.class})
    private String name;

    public interface Create {
    }

    public interface Update {
    }

}