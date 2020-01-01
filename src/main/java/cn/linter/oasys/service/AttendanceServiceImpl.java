package cn.linter.oasys.service;

import cn.linter.oasys.entity.Attendance;
import cn.linter.oasys.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    @Autowired
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        return attendanceMapper.selectAttendance(userId, date);
    }

    @Override
    public void signIn(int userId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datetime = format.format(new Date());
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11);
        attendanceMapper.signIn(userId, date, time);
    }

    @Override
    public void signOut(int userId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String datetime = format.format(new Date());
        String date = datetime.substring(0, 10);
        String time = datetime.substring(11);
        attendanceMapper.signOut(userId, date, time);
    }
}
