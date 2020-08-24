package cn.linter.oasys.service;

import cn.linter.oasys.entity.Attendance;
import cn.linter.oasys.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public List<String> getAttendances(int userId) {
        return attendanceMapper.selectAttendances(userId);
    }

    @Override
    public void setAttendanceTime(String begin, String end) {
        attendanceMapper.updateAttendanceTime(begin, end);
    }

    @Override
    public Map<String, String> getAttendanceTime() {
        return attendanceMapper.selectAttendanceTime();
    }

    @Override
    public Attendance getAttendance(int userId) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return attendanceMapper.selectAttendance(userId, date);
    }

    @Override
    public void signIn(int userId) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        attendanceMapper.signIn(userId, date, time);
    }

    @Override
    public void signOut(int userId) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        attendanceMapper.signOut(userId, date, time);
    }
}
