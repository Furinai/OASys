package cn.linter.oasys.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Role {

    /**
     * 主键ID
     */
    @NotNull(message = "角色ID不能为空", groups = {Update.class, UserNested.class})
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    public interface Create {
    }

    public interface Update {
    }

    public interface UserNested {
    }

}