package cn.linter.oasys.chat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Message {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 消息类型
     */
    public enum Type {
        /**
         * 系统消息
         */
        SYSTEM,
        /**
         * 公共消息
         */
        PUBLIC,
        /**
         * 私人消息
         */
        PRIVATE;
    }

}
