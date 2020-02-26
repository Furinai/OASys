package cn.linter.oasys.controller;

import cn.linter.oasys.entity.User;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ServerEndpoint("/notice")
public class NoticeWebSocket {
    public static ConcurrentMap<String, NoticeWebSocket> webSockets = new ConcurrentHashMap<>();
    public Session session;

    @OnOpen
    public void onOpen(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            String username = principal.getName();
            this.session = session;
            webSockets.put(username, this);
        }
    }

    @OnClose
    public void onClose(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            webSockets.remove(principal.getName());
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
