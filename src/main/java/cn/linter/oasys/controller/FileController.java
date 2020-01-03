package cn.linter.oasys.controller;

import cn.linter.oasys.entity.Response;
import cn.linter.oasys.service.FileService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getFiles")
    public Response getFile(@RequestParam("userId") int userId, @RequestParam("parentId") int parentId, @RequestParam("personal") boolean personal,
                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = fileService.getFiles(userId, parentId, personal, pageNumber, pageSize);
        return new Response("success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/addFolder")
    public Response addFolder(@RequestParam("userId") int userId, @RequestParam("parentId") int parentId,
                              @RequestParam("folderName") String folderName, @RequestParam("personal") boolean personal) {
        fileService.addFolder(folderName, userId, parentId, personal);
        return new Response("success", "添加成功！");
    }

}
