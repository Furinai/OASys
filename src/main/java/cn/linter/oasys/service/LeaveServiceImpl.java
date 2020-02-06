package cn.linter.oasys.service;

import cn.linter.oasys.entity.Leave;
import cn.linter.oasys.mapper.LeaveMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveMapper leaveMapper;

    public LeaveServiceImpl(LeaveMapper leaveMapper) {
        this.leaveMapper = leaveMapper;
    }

    @Override
    public void askLeave(Leave leave) {
        leave.setStatus(0);
        leaveMapper.askLeave(leave);
    }

    @Override
    public void checkLeave(Leave leave) {
        leaveMapper.checkLeave(leave);
    }

    @Override
    public PageInfo<?> getLeaves(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(leaveMapper.selectLeave());
    }
}
