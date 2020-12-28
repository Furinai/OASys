package cn.linter.oasys.attendance.service.impl;

import cn.linter.oasys.attendance.dao.AttendanceDao;
import cn.linter.oasys.attendance.entity.Attendance;
import cn.linter.oasys.attendance.service.AttendanceService;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
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
    public Attendance create(Attendance attendance) throws BusinessException {
        Attendance existingAttendance = attendanceDao.selectById(attendance.getId());
        if (existingAttendance != null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_CLOCKED_IN);
        }
        String workingHoursStart = attendanceDao.selectSettingValueByName("working_hours_start");
        Duration duration = Duration.between(LocalTime.parse(workingHoursStart), LocalTime.now());
        long lateMinutes = duration.toMinutes();
        if (lateMinutes > 0) {
            attendance.setComeLateMinutes((short) lateMinutes);
        }
        attendance.setClockDate(LocalDate.now());
        attendance.setClockInTime(LocalTime.now());
        attendanceDao.insert(attendance);
        return attendance;
    }

    @Override
    public Attendance update(Attendance attendance) throws BusinessException {
        Attendance oldAttendance = attendanceDao.selectById(attendance.getId());
        if (oldAttendance == null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_NOT_CLOCKED_IN);
        }
        if (oldAttendance.getClockOutTime() != null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_CLOCKED_OUT);
        }
        String workingHoursEnd = attendanceDao.selectSettingValueByName("working_hours_end");
        Duration duration = Duration.between(LocalTime.now(), LocalTime.parse(workingHoursEnd));
        long earlyMinutes = duration.toMinutes();
        if (earlyMinutes > 0) {
            attendance.setLeaveEarlyMinutes((short) earlyMinutes);
        }
        attendance.setClockOutTime(LocalTime.now());
        attendanceDao.updateById(attendance);
        return attendanceDao.selectById(attendance.getId());
    }

}