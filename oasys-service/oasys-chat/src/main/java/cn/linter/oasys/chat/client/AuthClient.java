package cn.linter.oasys.chat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 授权服务接口
 *
 * @author wangxiaoyang
 * @since 2021/1/1
 */
@FeignClient(name = "auth-service")
public interface AuthClient {

    /**
     * 查询授权信息
     *
     * @param token Token
     * @return 授权信息
     */
    @PostMapping("oauth/check_token")
    Map<String, Object> queryAuth(@RequestParam("token") String token);

}