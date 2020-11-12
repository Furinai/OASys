package cn.linter.oasys.attendance.controller;

import cn.linter.oasys.attendance.entity.Attendance;
import cn.linter.oasys.attendance.service.AttendanceService;
import cn.linter.oasys.common.entity.Response;
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
    public Response<Attendance> queryAttendance(@ApiParam("用户ID") Long userId) {
        return Response.sendSuccess(200, attendanceService.queryByUserId(userId));
    }

    @ApiOperation("通过用户ID查询某月考勤")
    @GetMapping("attendances")
    public Response<List<Attendance>> listAttendance(@ApiParam("用户ID") Long userId, @ApiParam("年") int year, @ApiParam("月") int month) {
        return Response.sendSuccess(200, attendanceService.listByUserId(userId, year, month));
    }

    @ApiOperation("签到")
    @PostMapping("attendance")
    public Response<Attendance> clockIn(@RequestBody @ApiParam("考勤") Attendance attendance) {
        Attendance existingAttendance = attendanceService.queryByUserId(attendance.getUserId());
        if (existingAttendance != null) {
            Response.sendError(400, "你已经签到过了！");
        }
        String workingHoursStart = attendanceService.querySettingByName("working_hours_start");
        Duration duration = Duration.between(LocalTime.parse(workingHoursStart), LocalTime.now());
        long differenceMinutes = duration.toMinutes();
        if (differenceMinutes > 0) {
            attendance.setClockDescription("迟到");
            return Response.sendSuccess(201, "签到成功，迟到" + differenceMinutes + "分钟", attendanceService.create(attendance));
        }
        return Response.sendSuccess(201, "签到成功！", attendanceService.create(attendance));
    }

    @ApiOperation("签退")
    @PutMapping("attendance")
    public Response<?> clockOut(@RequestBody @ApiParam("考勤") Attendance attendance) {
        Attendance existingAttendance = attendanceService.queryByUserId(attendance.getUserId());
        if (existingAttendance == null) {
            Response.sendError(400, "你还没有签到！");
        }
        String workingHoursEnd = attendanceService.querySettingByName("working_hours_end");
        Duration duration = Duration.between(LocalTime.now(), LocalTime.parse(workingHoursEnd));
        long differenceMinutes = duration.toMinutes();
        if (differenceMinutes > 0) {
            attendance.setClockDescription("早退");
            return Response.sendSuccess(200, "签退成功，早退" + differenceMinutes + "分钟", attendanceService.update(attendance));

        }
        return Response.sendSuccess(200, "签退成功", attendanceService.update(attendance));
    }

}