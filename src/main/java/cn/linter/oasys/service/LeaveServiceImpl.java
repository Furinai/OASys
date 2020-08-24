package cn.linter.oasys.service;

import cn.linter.oasys.entity.Leave;
import cn.linter.oasys.entity.Notice;
import cn.linter.oasys.mapper.LeaveMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveMapper leaveMapper;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public LeaveServiceImpl(LeaveMapper leaveMapper, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.leaveMapper = leaveMapper;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void askLeave(Leave leave) {
        leave.setStatus(0);
        leaveMapper.askLeave(leave);
    }

    @Override
    public void checkLeave(Leave leave) {
        String username = leave.getUser().getUsername();
        Notice notice = new Notice();
        notice.setReceiverName(username);
        LocalDateTime time = LocalDateTime.now();
        notice.setCreatedTime(time);
        if (leave.getStatus() == 1) {
            notice.setContent("你的请假通过了审核！");
        } else {
            notice.setContent("你的请假没有通过审核！");
        }
        String json = null;
        try {
            json = objectMapper.writeValueAsString(notice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send("notice", json);
        leaveMapper.checkLeave(leave);
    }

    @Override
    public PageInfo<Leave> getLeaves(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(leaveMapper.selectLeaves());
    }
}
