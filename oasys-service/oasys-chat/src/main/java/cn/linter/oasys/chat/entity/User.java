package cn.linter.oasys.chat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Data
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private static final long serialVersionUID = 3590006581560556909L;
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

}