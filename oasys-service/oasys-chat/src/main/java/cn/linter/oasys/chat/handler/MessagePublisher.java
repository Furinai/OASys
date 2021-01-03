package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.entity.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

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

    public void publish(WebSocketSession session, String content) {
        Map<String, Object> attributes = session.getAttributes();
        Message message = Message.builder()
                .content(content)
                .username((String) attributes.get("username"))
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
