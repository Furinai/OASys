package cn.linter.oasys.chat.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
public class Message implements Serializable {

    private static final long serialVersionUID = -4484722387608220685L;
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
    private LocalDateTime createTime;

}
