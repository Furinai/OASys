package cn.linter.oasys.service;

import cn.linter.oasys.controller.NoticeWebSocket;
import cn.linter.oasys.entity.Notice;
import cn.linter.oasys.mapper.NoticeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public NoticeServiceImpl(NoticeMapper noticeMapper, ObjectMapper objectMapper, UserService userService) {
        this.noticeMapper = noticeMapper;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    public List<Notice> getNotices(String username) {
        return noticeMapper.selectNotices(username);
    }

    @Override
    public void sendNotice(int id, String message) {
        String username = userService.getUserById(id).getUsername();
        sendMessage(username, message);
    }

    @Override
    public void sendNotice(String username, String message) {
        sendMessage(username, message);
    }


    @Override
    public void sendMessage(String username, String message) {
        ConcurrentMap<String, NoticeWebSocket> webSockets = NoticeWebSocket.webSockets;
        LocalDateTime time = LocalDateTime.now();
        Notice notice = new Notice();
        notice.setContent(message);
        notice.setCreatedTime(time);
        notice.setReceiverName(username);
        notice.setId(noticeMapper.insetNotice(notice));
        if (webSockets.containsKey(username)) {
            NoticeWebSocket webSocket = webSockets.get(username);
            try {
                String json = objectMapper.writeValueAsString(notice);
                webSocket.session.getBasicRemote().sendText(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void markRead(Integer[] ids) {
        noticeMapper.markRead(ids);
    }
}
