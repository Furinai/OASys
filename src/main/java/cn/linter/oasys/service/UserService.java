package cn.linter.oasys.service;

import cn.linter.oasys.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    int register(User user);

    User getUserById(int id);
}
