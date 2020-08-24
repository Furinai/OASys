package cn.linter.oasys.controller;

import cn.linter.oasys.entity.NetFile;
import cn.linter.oasys.entity.Response;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.service.NetFileService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class NetFileController {

    private final NetFileService netFileService;

    public NetFileController(NetFileService netFileService) {
        this.netFileService = netFileService;
    }

    @GetMapping("/getFiles")
    public Response<PageInfo<NetFile>> getNetFiles(@AuthenticationPrincipal User user, @RequestParam("parentId") int parentId, @RequestParam("personal") boolean personal,
                                                   @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<NetFile> pageInfo = netFileService.getNetFiles(user.getId(), parentId, personal, pageNumber, pageSize);
        return Response.success("获取成功！", pageInfo);
    }

    @GetMapping("/addFolder")
    public Response<?> addFolder(@AuthenticationPrincipal User user, @RequestParam("parentId") int parentId,
                                 @RequestParam("folderName") String folderName, @RequestParam("personal") boolean personal) {
        netFileService.addFolder(folderName, user, parentId, personal);
        return Response.success("添加成功！");
    }

    @PostMapping("/uploadFile")
    public Response<?> uploadFile(@RequestParam("parentId") int parentId, @RequestParam("personal") boolean personal,
                                  @AuthenticationPrincipal User user, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        netFileService.uploadFile(multipartFile, user, parentId, personal);
        return Response.success("上传成功！");
    }

    @GetMapping("/renameFile")
    public Response<?> renameNetFile(@RequestParam("id") int id, @RequestParam("newName") String newName) {
        netFileService.renameNetFile(id, newName);
        return Response.success("重命名成功！");
    }

    @PostMapping("/deleteFiles")
    public Response<?> deleteNetFiles(@RequestBody Integer[] ids) {
        netFileService.deleteNetFiles(ids);
        return Response.success("删除成功！");
    }
}
