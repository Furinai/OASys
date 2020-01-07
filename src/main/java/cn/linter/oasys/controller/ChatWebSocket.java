package cn.linter.oasys.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/chat")
public class ChatWebSocket {
    private static CopyOnWriteArraySet<ChatWebSocket> webSockets = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        session.setMaxIdleTimeout(3600000);
        this.session = session;
        webSockets.add(this);
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
        for (ChatWebSocket webSocket : webSockets) {
            webSocket.sendMessage(message);
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }
}
