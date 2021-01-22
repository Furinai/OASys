package cn.linter.oasys.chat.client;

import cn.linter.oasys.chat.entity.User;
import cn.linter.oasys.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务接口
 *
 * @author wangxiaoyang
 * @since 2021/1/1
 */
@FeignClient(name = "user-service")
public interface UserClient {

    /**
     * 查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @GetMapping("users/{username}")
    Result<User> queryUser(@PathVariable String username);

}
