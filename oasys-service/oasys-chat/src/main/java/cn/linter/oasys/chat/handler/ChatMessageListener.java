package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * 消息监听器
 *
 * @author wangxiaoyang
 * @since 2021/1/2
 */
@Slf4j
@Component
public class ChatMessageListener implements MessageListener<String> {

    @Override
    public void received(Consumer<String> consumer, Message<String> msg) {
        TextMessage textMessage = new TextMessage(msg.getValue());
        SessionContainer.values().forEach(session -> {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                log.error("消息发送失败", e);
            }
        });
        try {
            consumer.acknowledge(msg);
        } catch (PulsarClientException e) {
            log.error("消息应答失败", e);
        }
    }

}
