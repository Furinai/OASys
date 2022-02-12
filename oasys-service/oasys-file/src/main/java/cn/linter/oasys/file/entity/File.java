package cn.linter.oasys.file.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 文件实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Data
@ToString
@EqualsAndHashCode
public class File {

    /**
     * 主键ID
     */
    @NotNull(message = "文件ID不能为空", groups = {Update.class})
    private Long id;
    /**
     * 文件名
     */
    @Length(min = 1, max = 20, message = "文件（夹）名长度为 1 到 20 之间", groups = {CreateFolder.class, Update.class})
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 类型
     */
    private String type;
    /**
     * 大小
     */
    private String size;
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = {CreateFile.class, CreateFolder.class})
    private Long userId;
    /**
     * 创建者姓名
     */
    @NotBlank(message = "创建者姓名不能为空", groups = {CreateFile.class, CreateFolder.class})
    private String creator;
    /**
     * 父级ID
     */
    @NotNull(message = "父级目录ID不能为空", groups = {CreateFile.class, CreateFolder.class})
    private Long parentId;
    /**
     * 是否共享
     */
    private Boolean shared;
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
     * content-type
     */
    private String contentType;

    public boolean isFolder() {
        return "文件夹".equals(this.type);
    }

    public interface CreateFile {
    }

    public interface CreateFolder {
    }

    public interface Update {
    }

}