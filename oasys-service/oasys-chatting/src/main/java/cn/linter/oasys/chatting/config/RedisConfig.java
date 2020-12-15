package cn.linter.oasys.chatting.config;

import cn.linter.oasys.chatting.messaging.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

/**
 * Redis配置
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    ReactiveStringRedisTemplate template(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
    }

    @Bean
    RedisAtomicLong chatMessageCounter(RedisConnectionFactory redisConnectionFactory) {
        return new RedisAtomicLong("TOTAL_MESSAGE_COUNT", redisConnectionFactory);
    }

    @Bean
    RedisAtomicLong activeUserCounter(RedisConnectionFactory redisConnectionFactory) {
        return new RedisAtomicLong("ACTIVE_USER_COUNT", redisConnectionFactory);
    }

    @Bean
    ApplicationRunner applicationRunner(MessageListener redisMessageListener) {
        return args -> {
            redisMessageListener.subscribeMessageChannelAndPublishOnWebSocket()
                    .doOnSubscribe(subscription -> log.info("Redis Listener Started"))
                    .doOnError(throwable -> log.error("Error listening to Redis topic.", throwable))
                    .doFinally(signalType -> log.info("Stopped Listener. Signal Type: {}", signalType))
                    .subscribe();
        };
    }

}
