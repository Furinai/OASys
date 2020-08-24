package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Attendance;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/getAttendanceTime")
    public Response<List<String>> getAttendanceTime() {
        Map<String, String> map = attendanceService.getAttendanceTime();
        List<String> list = new ArrayList<>();
        list.add(map.get("begin"));
        list.add(map.get("end"));
        return Response.success("获取成功！", list);
    }

    @GetMapping("/setAttendanceTime")
    @PreAuthorize("hasAnyRole('主管','经理')")
    public Response<?> setAttendanceTime(@RequestParam("begin") String begin, @RequestParam("end") String end) {
        attendanceService.setAttendanceTime(begin, end);
        return Response.success("设置成功！");
    }

    @GetMapping("/getAttendance")
    public Response<Attendance> getAttendance(@AuthenticationPrincipal User user) {
        Attendance attendance = attendanceService.getAttendance(user.getId());
        return Response.success("获取成功！", attendance);
    }

    @GetMapping("/getAttendances")
    public Response<List<String>> getAttendances(@AuthenticationPrincipal User user) {
        List<String> attendances = attendanceService.getAttendances(user.getId());
        return Response.success("获取成功！", attendances);
    }

    @GetMapping("/signIn")
    public Response<?> signIn(@AuthenticationPrincipal User user) throws ParseException {
        Map<String, String> map = attendanceService.getAttendanceTime();
        LocalTime beginTime = LocalTime.parse(map.get("begin"));
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(beginTime.minusHours(1L))) {
            return Response.error("还未到签到时间！");
        }
        if (localTime.isAfter(beginTime.plusHours(1L))) {
            return Response.error("已超过签到时间！");
        }
        attendanceService.signIn(user.getId());
        return Response.success("签到成功！");
    }

    @GetMapping("/signOut")
    public Response<?> signOut(@AuthenticationPrincipal User user) throws ParseException {
        if (attendanceService.getAttendance(user.getId()) == null) {
            return Response.error("您未签到，不能签退！");
        }
        Map<String, String> map = attendanceService.getAttendanceTime();
        LocalTime endTime = LocalTime.parse(map.get("end"));
        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(endTime.minusHours(1L))) {
            return Response.error("还未到签退时间！");
        }
        if (localTime.isAfter(endTime.plusHours(1L))) {
            return Response.error("已超过签退时间！");
        }
        attendanceService.signOut(user.getId());
        return Response.success("签退成功！");
    }
}
