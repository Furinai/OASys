package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Leave;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.LeaveService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("askLeave")
    public Response askLeave(@AuthenticationPrincipal User user, @RequestBody Leave leave) {
        leave.setUser(user);
        leaveService.askLeave(leave);
        return new Response("success", "提交成功！");
    }

    @PostMapping("checkLeave")
    @PreAuthorize("hasRole('经理')")
    public Response checkLeave(@RequestBody Leave leave) {
        leaveService.checkLeave(leave);
        return new Response("success", "提交成功！");
    }


    @GetMapping("getLeaves")
    public Response getLeaves(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = leaveService.getLeaves(pageNumber, pageSize);
        return new Response("success", pageInfo.getTotal(), pageInfo.getList());
    }
}
