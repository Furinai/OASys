package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttendanceMapper {
    void updateAttendanceTime(@Param("begin") String begin, @Param("end") String end);

    Map<String, String> selectAttendanceTime();

    List<String> selectAttendances(@Param("userId") int userId);

    Attendance selectAttendance(@Param("userId") int userId, @Param("date") String date);

    void signIn(@Param("userId") int userId, @Param("date") LocalDate date, @Param("time") LocalTime time);

    void signOut(@Param("userId") int userId, @Param("date") LocalDate date, @Param("time") LocalTime time);
}
