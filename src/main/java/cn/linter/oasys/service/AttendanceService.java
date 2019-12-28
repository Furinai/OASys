package cn.linter.oasys.service;

import cn.linter.oasys.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    List<String> getAttendances(int userId);

    Attendance getAttendance(int userId);

    void signIn(int userId);

    void signOut(int userId);
}
