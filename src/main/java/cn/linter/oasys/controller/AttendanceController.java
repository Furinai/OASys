package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Attendance;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/attendances")
    public Response getAttendances(@AuthenticationPrincipal User user) {
        List<String> attendances = attendanceService.getAttendances(user.getId());
        return new Response("success", attendances);
    }

    @GetMapping("/attendance")
    public Response getAttendance(@AuthenticationPrincipal User user) {
        Attendance attendance = attendanceService.getAttendance(user.getId());
        return new Response("success", attendance);
    }

    @GetMapping("/signIn")
    public Response signIn(@AuthenticationPrincipal User user) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        if (date.before(format.parse("08:00:00"))) {
            return new Response("error", "还未到签到时间！");
        } else if (date.after(format.parse("10:00:00"))) {
            return new Response("error", "已超过签到时间！");
        }
        attendanceService.signIn(user.getId());
        return new Response("success", "签到成功！");
    }

    @GetMapping("/signOut")
    public Response signOut(@AuthenticationPrincipal User user) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        if (date.before(format.parse("16:00:00"))) {
            return new Response("error", "还未到签退时间！");
        } else if (date.after(format.parse("18:00:00"))) {
            return new Response("error", "已超过签退时间！");
        }
        attendanceService.signOut(user.getId());
        return new Response("success", "签退成功！");
    }
}
