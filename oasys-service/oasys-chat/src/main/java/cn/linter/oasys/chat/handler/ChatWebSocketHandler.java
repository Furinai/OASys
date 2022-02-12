package cn.linter.oasys.chat.handler;

import cn.linter.oasys.chat.container.SessionContainer;
import cn.linter.oasys.chat.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;


/**
 * 聊天WebSocket处理器
 *
 * @author wangxiaoyang
 * @since 2021/1/1
 */
@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatMessagePublisher chatMessagePublisher;

    public ChatWebSocketHandler(ChatMessagePublisher chatMessagePublisher) {
        this.chatMessagePublisher = chatMessagePublisher;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        SessionContainer.add(session);
        Map<String, Object> attributes = session.getAttributes();
        String content = attributes.get("fullName") + "进入了聊天室";
        chatMessagePublisher.publish(attributes, Message.Type.SYSTEM, content);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        chatMessagePublisher.publish(session.getAttributes(), Message.Type.PUBLIC, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        SessionContainer.remove(session);
        Map<String, Object> attributes = session.getAttributes();
        String content = attributes.get("fullName") + "离开了聊天室";
        chatMessagePublisher.publish(attributes, Message.Type.SYSTEM, content);
    }

}
