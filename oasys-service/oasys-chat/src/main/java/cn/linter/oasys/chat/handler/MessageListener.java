package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import cn.linter.oasys.chat.entity.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Map;

/**
 * 消息监听器
 *
 * @author wangxiaoyang
 * @since 2021/1/2
 */
@Component
public class MessageListener {

    private final ObjectMapper objectMapper;

    public MessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "public-chat")
    public void listen(ConsumerRecord<String, String> record) {
        Iterator<WebSocketSession> iterator = SessionContainer.iterator();
        while (iterator.hasNext()) {
            WebSocketSession session = iterator.next();
            Map<String, Object> map = session.getAttributes();
            Message message = Message.builder()
                    .content(record.value())
                    .username((String) map.get("username"))
                    .profilePicture((String) map.get("profilePicture"))
                    .createTime(Instant.ofEpochMilli(record.timestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime()).build();
            try {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
