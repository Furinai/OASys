package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttendanceMapper {
    void updateAttendanceTime(@Param("begin") String begin, @Param("end") String end);

    Map<String, String> selectAttendanceTime();

    List<String> selectAttendances(@Param("userId") int userId);

    Attendance selectAttendance(@Param("userId") int userId, @Param("date") String date);

    void signIn(@Param("userId") int userId, @Param("date") String date, @Param("time") String time);

    void signOut(@Param("userId") int userId, @Param("date") String date, @Param("time") String time);
}
