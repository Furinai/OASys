package cn.linter.oasys.attendance.controller;

import cn.linter.oasys.attendance.entity.Attendance;
import cn.linter.oasys.attendance.service.AttendanceService;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考勤控制器
 *
 * @author wangxiaoyang
 * @since 2020/12/20
 */
@RestController
@RequestMapping("attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public Result<List<Attendance>> listAttendance(Long userId, Integer year, Integer month, Integer day) {
        return Result.of(ResultStatus.SUCCESS, attendanceService.listByUserIdAndClockDate(userId, year, month, day));
    }

    @PostMapping
    public Result<Attendance> clockIn(@RequestBody Attendance attendance) {
        return Result.of(ResultStatus.SUCCESS, attendanceService.create(attendance));
    }

    @PutMapping
    public Result<Attendance> clockOut(@RequestBody Attendance attendance) {
        return Result.of(ResultStatus.SUCCESS, attendanceService.update(attendance));
    }

}