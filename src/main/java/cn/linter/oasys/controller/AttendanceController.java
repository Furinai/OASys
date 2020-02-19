package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Attendance;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/getAttendanceTime")
    public Response getAttendanceTime() {
        Map<String, String> map = attendanceService.getAttendanceTime();
        List<String> list = new ArrayList<>();
        list.add(map.get("begin"));
        list.add(map.get("end"));
        return new Response("success", list);
    }

    @GetMapping("/setAttendanceTime")
    @PreAuthorize("hasAnyRole('主管','经理')")
    public Response setAttendanceTime(@RequestParam("begin") String begin,
                                      @RequestParam("end") String end) {
        attendanceService.setAttendanceTime(begin, end);
        return new Response("success", "设置成功！");
    }

    @GetMapping("/getAttendance")
    public Response getAttendance(@AuthenticationPrincipal User user) {
        Attendance attendance = attendanceService.getAttendance(user.getId());
        return new Response("success", attendance);
    }

    @GetMapping("/getAttendances")
    public Response getAttendances(@AuthenticationPrincipal User user) {
        List<String> attendances = attendanceService.getAttendances(user.getId());
        return new Response("success", attendances);
    }

    @GetMapping("/signIn")
    public Response signIn(@AuthenticationPrincipal User user) throws ParseException {
        Map<String, String> map = attendanceService.getAttendanceTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date time = format.parse(format.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(map.get("begin")));
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        if (time.before(calendar.getTime())) {
            return new Response("error", "还未到签到时间！");
        }
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        if (time.after(calendar.getTime())) {
            return new Response("error", "已超过签到时间！");
        }
        attendanceService.signIn(user.getId());
        return new Response("success", "签到成功！");
    }

    @GetMapping("/signOut")
    public Response signOut(@AuthenticationPrincipal User user) throws ParseException {
        if (attendanceService.getAttendance(user.getId()) == null) {
            return new Response("error", "您未签到，不能签退！");
        }
        Map<String, String> map = attendanceService.getAttendanceTime();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date time = format.parse(format.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(map.get("end")));
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        if (time.before(calendar.getTime())) {
            return new Response("error", "还未到签退时间！");
        }
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        if (time.after(calendar.getTime())) {
            return new Response("error", "已超过签退时间！");
        }
        attendanceService.signOut(user.getId());
        return new Response("success", "签退成功！");
    }
}
