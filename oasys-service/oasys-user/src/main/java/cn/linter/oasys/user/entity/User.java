package cn.linter.oasys.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private static final long serialVersionUID = -21242857091255014L;
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 部门
     */
    private Dept dept;
    /**
     * 邮箱地址
     */
    private String emailAddress;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 头像链接
     */
    private String profilePicture;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}