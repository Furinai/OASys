package cn.linter.oasys.search.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "file")
public class File implements Serializable {

    private static final long serialVersionUID = 223089641003572200L;
    /**
     * 主键ID
     */
    @Id
    private Long id;
    /**
     * 文件名
     */
    @Field(analyzer = "ik_max_word", name = "name", type = FieldType.Text)
    private String name;
    /**
     * 路径
     */
    @Field(name = "path", type = FieldType.Text)
    private String path;
    /**
     * 类型
     */
    @Field(name = "type", type = FieldType.Text)
    private String type;
    /**
     * 大小
     */
    @Field(name = "size", type = FieldType.Text)
    private String size;
    /**
     * 用户ID
     */
    @Field(name = "user_id", type = FieldType.Long)
    private Long userId;
    /**
     * 创建者姓名
     */
    @Field(name = "creator", type = FieldType.Text)
    private String creator;
    /**
     * 父级ID
     */
    @Field(name = "parent_id", type = FieldType.Long)
    private Long parentId;
    /**
     * 是否共享
     */
    @Field(name = "shared", type = FieldType.Boolean)
    private Boolean shared;
    /**
     * 创建时间
     */
    @Field(name = "create_time", type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @Field(name = "update_time", type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime updateTime;
    /**
     * content-type
     */
    @Field(name = "content_type", type = FieldType.Text)
    private String contentType;

    public boolean isFolder() {
        return "文件夹".equals(this.type);
    }

}