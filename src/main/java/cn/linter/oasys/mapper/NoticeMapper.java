package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<Notice> selectNotices(@Param("username") String username);

    int insetNotice(Notice notice);

    void markRead(@Param("ids") Integer[] ids);
}
