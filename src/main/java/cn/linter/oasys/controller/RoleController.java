package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.Role;
import cn.linter.oasys.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getRoles")
    public Response<List<Role>> getRoles() {
        return Response.success("获取成功！", roleService.getRoles());
    }
}
