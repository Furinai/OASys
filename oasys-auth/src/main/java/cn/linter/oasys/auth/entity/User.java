package cn.linter.oasys.auth.entity;

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
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 部门名
     */
    private String deptName;
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

}