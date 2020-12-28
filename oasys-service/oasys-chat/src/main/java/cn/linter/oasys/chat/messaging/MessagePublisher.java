package cn.linter.oasys.chat.messaging;

import cn.linter.oasys.chat.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 消息发布者
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@Slf4j
@Component
public class MessagePublisher {

    private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    private final RedisAtomicInteger chatMessageCounter;
    private final RedisAtomicLong activeUserCounter;
    private final ObjectMapper objectMapper;

    public MessagePublisher(ReactiveStringRedisTemplate reactiveStringRedisTemplate, RedisAtomicInteger chatMessageCounter, RedisAtomicLong activeUserCounter, ObjectMapper objectMapper) {
        this.reactiveStringRedisTemplate = reactiveStringRedisTemplate;
        this.chatMessageCounter = chatMessageCounter;
        this.activeUserCounter = activeUserCounter;
        this.objectMapper = objectMapper;
    }

    public Mono<Long> publishMessage(String message) {
        Integer totalMessage = chatMessageCounter.incrementAndGet();
        return Mono.fromCallable(() -> {
            try {
                return InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                log.error("Error getting hostname.", e);
            }
            return "localhost";
        }).map(hostName -> {
            //Message chatMessage = new Message(totalMessage, message, hostName, activeUserCounter.get());
            Message chatMessage = new Message();
            String chatString = "EMPTY_MESSAGE";
            try {
                chatString = objectMapper.writeValueAsString(chatMessage);
            } catch (JsonProcessingException e) {
                log.error("Error converting Message {} into string", chatMessage, e);
            }
            return chatString;
        }).flatMap(chatString -> {
            // Publish Message to Redis Channels
            return reactiveStringRedisTemplate.convertAndSend("MESSAGE", chatString)
                    .doOnSuccess(aLong -> log.debug("Message published to Redis Topic."))
                    .doOnError(throwable -> log.error("Error publishing message.", throwable));
        });
    }

}
