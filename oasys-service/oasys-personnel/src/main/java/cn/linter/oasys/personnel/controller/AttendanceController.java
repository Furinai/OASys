package cn.linter.oasys.personnel.controller;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.common.exception.BusinessException;
import cn.linter.oasys.personnel.entity.Attendance;
import cn.linter.oasys.personnel.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * 考勤控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/12
 */
@Api(tags = "考勤控制器")
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @ApiOperation("通过用户ID查询当天考勤")
    @GetMapping("attendance")
    public Result<Attendance> queryAttendance(@ApiParam("用户ID") Long userId) {
        return Result.of(ResultStatus.SUCCESS, attendanceService.queryByUserId(userId));
    }

    @ApiOperation("通过用户ID查询某月考勤")
    @GetMapping("attendances")
    public Result<List<Attendance>> listAttendance(@ApiParam("用户ID") Long userId, @ApiParam("年") int year, @ApiParam("月") int month) {
        return Result.of(ResultStatus.SUCCESS, attendanceService.listByUserId(userId, year, month));
    }

    @ApiOperation("签到")
    @PostMapping("attendance")
    public Result<Attendance> clockIn(@RequestBody @ApiParam("考勤") Attendance attendance) {
        Attendance existingAttendance = attendanceService.queryByUserId(attendance.getUserId());
        if (existingAttendance != null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_CLOCKED_IN);
        }
        String workingHoursStart = attendanceService.querySettingByName("working_hours_start");
        Duration duration = Duration.between(LocalTime.parse(workingHoursStart), LocalTime.now());
        long differenceMinutes = duration.toMinutes();
        if (differenceMinutes > 0) {
            attendance.setClockDescription("迟到");
            return Result.of(ResultStatus.SUCCESS, attendanceService.create(attendance));
        }
        return Result.of(ResultStatus.SUCCESS, attendanceService.create(attendance));
    }

    @ApiOperation("签退")
    @PutMapping("attendance")
    public Result<Attendance> clockOut(@RequestBody @ApiParam("考勤") Attendance attendance) {
        Attendance existingAttendance = attendanceService.queryByUserId(attendance.getUserId());
        if (existingAttendance == null) {
            throw new BusinessException(ResultStatus.TODAY_HAS_NOT_CLOCKED_IN);
        }
        String workingHoursEnd = attendanceService.querySettingByName("working_hours_end");
        Duration duration = Duration.between(LocalTime.now(), LocalTime.parse(workingHoursEnd));
        long differenceMinutes = duration.toMinutes();
        if (differenceMinutes > 0) {
            if (attendance.getClockDescription() == null) {
                attendance.setClockDescription("早退");
            } else {
                attendance.setClockDescription(attendance.getClockDescription() + " 早退");
            }
            return Result.of(ResultStatus.SUCCESS, attendanceService.update(attendance));
        }
        return Result.of(ResultStatus.SUCCESS, attendanceService.update(attendance));
    }

}