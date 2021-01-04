package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.entity.Message;
import cn.linter.oasys.chat.entity.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 消息发布器
 *
 * @author wangxiaoyang
 * @date 2021/1/2
 */
@Component
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(Map<String, Object> attributes, Type type, String content) {
        Message message = Message.builder()
                .type(type)
                .content(content)
                .username((String) attributes.get("username"))
                .fullName((String) attributes.get("fullName"))
                .profilePicture((String) attributes.get("profilePicture"))
                .createTime(LocalDateTime.now())
                .build();
        try {
            String messageString = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("public-chat", messageString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}