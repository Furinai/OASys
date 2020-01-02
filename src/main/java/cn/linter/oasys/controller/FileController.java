package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.FileService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public Response getFile(@AuthenticationPrincipal User user, @RequestParam("parentId") int parentId,
                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = fileService.getFiles(user.getId(), parentId, pageNumber, pageSize);
        return new Response("success", pageInfo.getTotal(), pageInfo.getList());
    }
}
