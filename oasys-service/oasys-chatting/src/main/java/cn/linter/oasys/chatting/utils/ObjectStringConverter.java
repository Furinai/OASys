package cn.linter.oasys.chatting.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 对象和字符串转换器
 *
 * @author wangxiaoyang
 * @since 2020/11/17
 */
@Slf4j
public class ObjectStringConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> Mono<T> stringToObject(String data, Class<T> clazz) {
        return Mono.fromCallable(() -> OBJECT_MAPPER.readValue(data, clazz))
                .doOnError(throwable -> log.error("Error converting [{}] to class '{}'.", data, clazz.getSimpleName()));
    }

    public static <T> Mono<String> objectToString(T object) {
        return Mono.fromCallable(() -> OBJECT_MAPPER.writeValueAsString(object))
                .doOnError(throwable -> log.error("Error converting [{}] to String.", object));
    }

}
