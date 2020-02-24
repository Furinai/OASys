package cn.linter.oasys.service;

import cn.linter.oasys.controller.NoticeWebSocket;
import cn.linter.oasys.entity.Notice;
import cn.linter.oasys.mapper.NoticeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper, ObjectMapper objectMapper) {
        this.noticeMapper = noticeMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Notice> getNotices(String username) {
        return noticeMapper.selectNotices(username);
    }

    @Override
    public void sendNotice(String message, String username) {
        ConcurrentMap<String, NoticeWebSocket> webSockets = NoticeWebSocket.webSockets;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Notice notice = new Notice(message, timestamp, username);
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
