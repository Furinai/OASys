package cn.linter.oasys.chat.config;

import cn.linter.oasys.chat.entity.Message;
import cn.linter.oasys.chat.handler.ChatWebSocketHandler;
import cn.linter.oasys.chat.messaging.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.DirectProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket配置
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@Slf4j
@Configuration
public class WebSocketConfig {

    @Bean
    public ChatWebSocketHandler webSocketHandler(MessagePublisher redisMessagePublisher, RedisAtomicLong activeUserCounter) {
        DirectProcessor<Message> messageDirectProcessor = DirectProcessor.create();
        return new ChatWebSocketHandler(messageDirectProcessor, redisMessagePublisher, activeUserCounter);
    }

    @Bean
    public HandlerMapping webSocketHandlerMapping(ChatWebSocketHandler webSocketHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/chat", webSocketHandler);
        return new SimpleUrlHandlerMapping(map, -1);
        /*SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setCorsConfigurations(Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(map);
        return handlerMapping;*/
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
