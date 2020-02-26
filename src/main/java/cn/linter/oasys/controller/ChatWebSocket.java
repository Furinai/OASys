package cn.linter.oasys.controller;

import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@ServerEndpoint("/chat")
public class ChatWebSocket {
    public static ConcurrentMap<String, ChatWebSocket> webSockets = new ConcurrentHashMap<>();
    public static Map<String, Object> map = new HashMap<>();
    public static ObjectMapper objectMapper;
    public static UserService userService;
    public static int number;
    public Session session;
    public User user;

    @Autowired
    public void setChatWebSocket(ObjectMapper objectMapper, UserService userService) {
        ChatWebSocket.userService = userService;
        ChatWebSocket.objectMapper = objectMapper;
    }

    @OnOpen
    public void onOpen(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            String username = principal.getName();
            this.user = (User) userService.loadUserByUsername(username);
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

    @OnMessage
    public void onMessage(String message) throws JsonProcessingException {
        map.put("id", number++);
        map.put("username", user.getUsername());
        map.put("picture", user.getPicture());
        map.put("text", message);
        message = objectMapper.writeValueAsString(map);
        for (ChatWebSocket webSocket : webSockets.values()) {
            webSocket.session.getAsyncRemote().sendText(message);
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
