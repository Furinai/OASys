package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * 聊天WebSocket处理器
 *
 * @author wangxiaoyang
 * @since 2021/1/1
 */
@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final MessagePublisher messagePublisher;

    public ChatWebSocketHandler(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        SessionContainer.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        messagePublisher.publish(session,message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        SessionContainer.remove(session);
    }

}
