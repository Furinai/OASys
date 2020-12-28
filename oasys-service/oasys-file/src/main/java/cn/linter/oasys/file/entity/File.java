package cn.linter.oasys.file.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
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
public class File implements Serializable {

    private static final long serialVersionUID = 223089641003572200L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 文件名
     */
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
    private Long userId;
    /**
     * 用户姓名
     */
    private String creator;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 是否共享
     */
    private Boolean shared;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * content-type
     */
    private String contentType;

    public boolean isFolder() {
        return "文件夹".equals(this.type);
    }

}