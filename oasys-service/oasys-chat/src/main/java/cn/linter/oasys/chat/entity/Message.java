package cn.linter.oasys.chat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息实体类
 *
 * @author wangxiaoyang
 * @date 2020/11/17
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
@Document
public class Message implements Serializable {

    private static final long serialVersionUID = -4484722387608220685L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 类型
     */
    private Type type;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 用户头像
     */
    private String profilePicture;
    /**
     * 创建时间
     */
    @Indexed
    private LocalDateTime createTime;

}
