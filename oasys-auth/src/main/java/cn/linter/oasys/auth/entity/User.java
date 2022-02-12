package cn.linter.oasys.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public class User implements UserDetails {

    /**
     * 主键ID
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
     * 权限列表
     */
    private List<? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return 密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * @return 账户是否未过期
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return 账户是否未锁定
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return 凭证是否未过期
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return 是否激活
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}