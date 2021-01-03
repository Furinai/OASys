package cn.linter.oasys.chat.handler;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发布器
 *
 * @author wangxiaoyang
 * @date 2021/1/2
 */
@Component
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String message) {
        kafkaTemplate.send("public-chat", message);
    }

}
