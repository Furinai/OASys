package cn.linter.oasys.service;

import cn.linter.oasys.entity.Attendance;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    void setAttendanceTime(String begin, String end);

    Map<String, String> getAttendanceTime();

    List<String> getAttendances(int userId);

    Attendance getAttendance(int userId);

    void signIn(int userId);

    void signOut(int userId);
}
