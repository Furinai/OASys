package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMapper {
    void askLeave(Leave leave);

    void checkLeave(Leave leave);

    List<Leave> selectLeaves();
}
