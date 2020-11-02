package cn.linter.oasys.gateway.config;

import cn.linter.oasys.common.entity.Role;
import cn.linter.oasys.common.entity.User;
import cn.linter.oasys.gateway.entiry.Principal;
import cn.linter.oasys.gateway.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailService配置
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Configuration
public class UserDetailServiceConfig {

    private final UserService userService;

    public UserDetailServiceConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.getUserByUsername(username).getData();
            List<Role> roles = userService.getAllRoleOfUser(user.getId()).getData();
            return new Principal(user, roles.stream()
                    .map(role -> "ROLE_" + role.getName())
                    .collect(Collectors.toList()));
        };
    }

}
