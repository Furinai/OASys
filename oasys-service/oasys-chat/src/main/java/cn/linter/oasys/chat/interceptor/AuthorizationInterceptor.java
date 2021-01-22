package cn.linter.oasys.chat.interceptor;

import cn.linter.oasys.chat.client.AuthClient;
import cn.linter.oasys.chat.client.UserClient;
import cn.linter.oasys.chat.entity.User;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 授权拦截器
 *
 * @author wangxiaoyang
 */
@Slf4j
@Component
public class AuthorizationInterceptor implements HandshakeInterceptor {

    private final AuthClient authClient;
    private final UserClient userClient;

    public AuthorizationInterceptor(AuthClient authClient, UserClient userClient) {
        this.authClient = authClient;
        this.userClient = userClient;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String queryParam = request.getURI().getQuery();
        if (queryParam == null) {
            return false;
        }
        String rule = "([^&=]+)=([^&]*)";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(queryParam);
        Map<String, String> paramsMap = new HashMap<>();
        while (matcher.find()) {
            paramsMap.put(matcher.group(1), matcher.group(2));
        }
        String token = paramsMap.get("token");
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        Map<String, Object> auth;
        try {
            auth = authClient.queryAuth(token);
        } catch (FeignException e) {
            log.warn("Websocket authentication failed");
            return false;
        }
        String username = (String) auth.get("user_name");
        User user = userClient.queryUser(username).getData();
        attributes.put("token", token);
        attributes.put("username", username);
        attributes.put("fullName", user.getFullName());
        attributes.put("profilePicture", user.getProfilePicture());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, @Nullable Exception exception) {
    }

}