package cn.linter.oasys.personnel.service.impl;

import cn.linter.oasys.personnel.dao.AttendanceDao;
import cn.linter.oasys.personnel.entity.Attendance;
import cn.linter.oasys.personnel.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * 考勤服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public String querySettingByName(String name) {
        return attendanceDao.selectSettingByName(name);
    }

    @Override
    public Attendance queryByUserId(Long userId) {
        return attendanceDao.selectByUserId(userId);
    }

    @Override
    public List<Attendance> listByUserId(Long userId, int year, int month) {
        return attendanceDao.listByUserId(userId, year, month);
    }

    @Override
    public Attendance create(Attendance attendance) {
        attendance.setClockInTime(LocalTime.now());
        attendanceDao.insert(attendance);
        return attendance;
    }

    @Override
    public Attendance update(Attendance attendance) {
        attendance.setClockOutTime(LocalTime.now());
        attendanceDao.update(attendance);
        return queryByUserId(attendance.getUserId());
    }

}