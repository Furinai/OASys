package cn.linter.oasys.chat.container;

import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Session容器
 *
 * @author wangxiaoyang
 * @since 2021/1/2
 */
public class SessionContainer {

    private static final ConcurrentHashMap<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    public static void add(WebSocketSession session) {
        String username = (String) session.getAttributes().get("username");
        SESSIONS.put(username, session);
    }

    public static void remove(WebSocketSession session) {
        String username = (String) session.getAttributes().get("username");
        SESSIONS.remove(username);
    }

    public static Collection<WebSocketSession> values() {
        return SESSIONS.values();
    }

}
