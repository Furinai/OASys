package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * 消息监听器
 *
 * @author wangxiaoyang
 * @since 2021/1/2
 */
@Component
public class MessageListener {

    @KafkaListener(topics = "public-chat")
    public void listen(ConsumerRecord<String, String> record) {
        for (WebSocketSession session : SessionContainer.values()) {
            try {
                session.sendMessage(new TextMessage(record.value()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
