package cn.linter.oasys;

import cn.linter.oasys.entity.Role;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OasysApplicationTests {

    private final UserService userService;

    @Autowired
    OasysApplicationTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void addUser() {
        Role role = new Role();
        role.setId(1);
        User user = new User();
        user.setUsername("员工一");
        user.setPassword("1234");
        user.setSynopsis("这是员工一");
        user.setPicture("/img/picture/4.jpg");
        user.setRole(role);
        userService.addUser(user);
    }
}
