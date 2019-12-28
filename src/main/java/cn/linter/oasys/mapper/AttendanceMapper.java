package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    List<String> selectAttendances(@Param("userId") int userId);

    Attendance selectAttendance(@Param("userId") int userId, @Param("date") String date);

    void signIn(@Param("userId") int userId, @Param("date") String date, @Param("time") String time);

    void signOut(@Param("userId") int userId, @Param("date") String date, @Param("time") String time);
}
