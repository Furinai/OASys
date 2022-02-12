package cn.linter.oasys.attendance.service.impl;

import cn.linter.oasys.attendance.dao.AttendanceDao;
import cn.linter.oasys.attendance.entity.Attendance;
import cn.linter.oasys.attendance.service.AttendanceService;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private final AttendanceDao attendanceDao;

    public AttendanceServiceImpl(AttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }

    @Override
    public List<Attendance> listByUserIdAndClockDate(Long userId, Integer year, Integer month, Integer day) {
        return attendanceDao.listByUserIdAndClockDate(userId, year, month, day);
    }

    @Override
    public Attendance create(Long userId) throws BusinessException {
        LocalDateTime nowDateTime = LocalDateTime.now();
        int year = nowDateTime.getYear();
        int month = nowDateTime.getMonthValue();
        int day = nowDateTime.getDayOfMonth();
        if (attendanceDao.listByUserIdAndClockDate(userId, year, month, day).size() > 0) {
            throw new BusinessException(ResultStatus.TODAY_HAS_CLOCKED_IN);
        }
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        String workingHoursStart = attendanceDao.selectSettingValueByName("working_hours_start");
        Duration duration = Duration.between(LocalTime.parse(workingHoursStart), LocalTime.now());
        long lateMinutes = duration.toMinutes();
        if (lateMinutes > 0) {
            attendance.setComeLateMinutes((int) lateMinutes);
        }
        attendance.setClockDate(LocalDate.now());
        attendance.setClockInTime(LocalTime.now());
        attendanceDao.insert(attendance);
        return attendance;
    }

    @Override
    public Attendance update(Long id) throws BusinessException {
        Attendance attendance = attendanceDao.selectById(id);
        if (attendance == null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_NOT_CLOCKED_IN);
        }
        if (attendance.getClockOutTime() != null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_CLOCKED_OUT);
        }
        String workingHoursEnd = attendanceDao.selectSettingValueByName("working_hours_end");
        Duration duration = Duration.between(LocalTime.now(), LocalTime.parse(workingHoursEnd));
        long earlyMinutes = duration.toMinutes();
        if (earlyMinutes > 0) {
            attendance.setLeaveEarlyMinutes((int) earlyMinutes);
        }
        attendance.setClockOutTime(LocalTime.now());
        attendanceDao.updateById(attendance);
        return attendanceDao.selectById(attendance.getId());
    }

}