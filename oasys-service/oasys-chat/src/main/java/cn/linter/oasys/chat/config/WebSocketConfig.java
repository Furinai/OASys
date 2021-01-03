package cn.linter.oasys.chat.config;

import cn.linter.oasys.chat.handler.ChatWebSocketHandler;
import cn.linter.oasys.chat.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;
    private final AuthorizationInterceptor authorizationInterceptor;

    public WebSocketConfig(ChatWebSocketHandler chatWebSocketHandler, AuthorizationInterceptor authorizationInterceptor) {
        this.chatWebSocketHandler = chatWebSocketHandler;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/chat")
                .addInterceptors(authorizationInterceptor).setAllowedOrigins("*");
    }

}
