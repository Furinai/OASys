package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

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
        TextMessage textMessage = new TextMessage(record.value());
        SessionContainer.values().parallelStream().forEach(session -> {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
