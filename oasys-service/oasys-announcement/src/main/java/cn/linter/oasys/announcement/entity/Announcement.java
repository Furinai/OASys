package cn.linter.oasys.announcement.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@Data
@ToString
@EqualsAndHashCode
public class Announcement implements Serializable {

    private static final long serialVersionUID = 673395149825805162L;
    /**
     * 主键ID
     */
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {Create.class})
    @Length(min = 5, max = 50, message = "标题长度为 5 到 50 之间", groups = {Create.class, Update.class})
    private String title;
    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = {Create.class})
    @Length(min = 5, max = 2000, message = "内容长度为 5 到 2000 之间", groups = {Create.class, Update.class})
    private String content;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public interface Create {
    }

    public interface Update {
    }

}