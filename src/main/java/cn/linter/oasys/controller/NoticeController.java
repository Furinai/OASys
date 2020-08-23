package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Notice;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.NoticeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/getNotices")
    public Response<List<Notice>> getNotices(@AuthenticationPrincipal User user) {
        List<Notice> notices = noticeService.getNotices(user.getUsername());
        return Response.success("获取成功！", notices);
    }

    @PostMapping("/markRead")
    public Response<?> markRead(@RequestBody Integer[] ids) {
        noticeService.markRead(ids);
        return Response.success("成功标记已读！");
    }
}
