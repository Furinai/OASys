package cn.linter.oasys.service;

import cn.linter.oasys.entity.Leave;
import cn.linter.oasys.mapper.LeaveMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveMapper leaveMapper;
    private final NoticeService noticeService;

    @Autowired
    public LeaveServiceImpl(LeaveMapper leaveMapper, NoticeService noticeService) {
        this.leaveMapper = leaveMapper;
        this.noticeService = noticeService;
    }

    @Override
    public void askLeave(Leave leave) {
        leave.setStatus(0);
        leaveMapper.askLeave(leave);
    }

    @Override
    public void checkLeave(Leave leave) {
        if (leave.getStatus() == 1) {
            noticeService.sendNotice(leave.getUser().getUsername(), "你的请假通过了审核！");
        } else {
            noticeService.sendNotice(leave.getUser().getUsername(), "你的请假没有通过审核！");
        }
        leaveMapper.checkLeave(leave);
    }

    @Override
    public PageInfo<?> getLeaves(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(leaveMapper.selectLeaves());
    }
}
