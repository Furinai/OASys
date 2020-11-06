package cn.linter.oasys.oauth.entity;

import cn.linter.oasys.common.entity.Role;
import cn.linter.oasys.common.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 认证用户实体类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public class Principal implements UserDetails {

    private static final long serialVersionUID = 8884273058722672541L;

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
     * 权限列表
     */
    private List<GrantedAuthority> authorities;

    /**
     * 无参数构造方法
     */
    public Principal() {
    }

    /**
     * 参数为 User和 Roles的构造方法
     *
     * @param user  用户
     * @param roles Role类型角色列表
     */
    public Principal(User user, List<Role> roles) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = roles.stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * @return 密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return 权限列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * @return 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return 凭证是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return 是否激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}