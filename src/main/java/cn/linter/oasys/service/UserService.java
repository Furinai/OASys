package cn.linter.oasys.service;

import cn.linter.oasys.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void addUser(User user);

    User getUserById(int id);
}
