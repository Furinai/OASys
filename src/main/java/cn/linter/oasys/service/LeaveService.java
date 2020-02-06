package cn.linter.oasys.service;

import cn.linter.oasys.entity.Leave;
import com.github.pagehelper.PageInfo;

public interface LeaveService {
    void askLeave(Leave leave);

    void checkLeave(Leave leave);

    PageInfo<?> getLeaves(int pageNumber, int pageSize);
}
