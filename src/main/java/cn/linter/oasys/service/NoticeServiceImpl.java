package cn.linter.oasys.service;

import cn.linter.oasys.entity.Notice;
import cn.linter.oasys.mapper.NoticeMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final ObjectMapper objectMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper, ObjectMapper objectMapper) {
        this.noticeMapper = noticeMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Notice> getNotices(String username) {
        return noticeMapper.selectNotices(username);
    }

    @Override
    public void markRead(Integer[] ids) {
        noticeMapper.markRead(ids);
    }

    @KafkaListener(topics = "notice")
    public void onListenNotice(ConsumerRecord<String, String> cr) {
        Notice notice = null;
        try {
            notice = objectMapper.readValue(cr.value(), Notice.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        noticeMapper.insetNotice(notice);
    }
}
