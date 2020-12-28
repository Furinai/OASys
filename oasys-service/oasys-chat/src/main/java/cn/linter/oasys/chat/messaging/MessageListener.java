package cn.linter.oasys.chat.messaging;

import cn.linter.oasys.chat.entity.Message;
import cn.linter.oasys.chat.handler.ChatWebSocketHandler;
import cn.linter.oasys.chat.utils.ObjectStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 消息监听者
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@Slf4j
@Component
public class MessageListener {

    private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    private final ChatWebSocketHandler chatWebSocketHandler;

    public MessageListener(ReactiveStringRedisTemplate reactiveStringRedisTemplate, ChatWebSocketHandler chatWebSocketHandler) {
        this.reactiveStringRedisTemplate = reactiveStringRedisTemplate;
        this.chatWebSocketHandler = chatWebSocketHandler;
    }

    public Mono<Void> subscribeMessageChannelAndPublishOnWebSocket() {
        return reactiveStringRedisTemplate.listenTo(new PatternTopic("MESSAGE"))
                .map(ReactiveSubscription.Message::getMessage)
                .flatMap(message -> ObjectStringConverter.stringToObject(message, Message.class))
                .flatMap(chatWebSocketHandler::sendMessage)
                .then();
    }

}
