package cn.linter.oasys.information.controller;

import cn.linter.oasys.common.entity.Response;
import cn.linter.oasys.information.entity.Announcement;
import cn.linter.oasys.information.service.AnnouncementService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@Api(tags = "公告控制器")
@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @ApiOperation("分页查询所有公告")
    @GetMapping("announcements")
    public Response<List<Announcement>> listAnnouncement(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                                         @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<Announcement> pageInfo = announcementService.list(pageNumber, pageSize);
        return Response.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    @ApiOperation("新增公告")
    @PostMapping("announcement")
    public Response<Announcement> createAnnouncement(@RequestBody @ApiParam("公告实例") Announcement announcement) {
        return Response.sendSuccess(201, announcementService.create(announcement));
    }

    @ApiOperation("更新公告")
    @PutMapping("announcement")
    public Response<Announcement> updateAnnouncement(@RequestBody @ApiParam("公告实例") Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.update(announcement);
        if (updatedAnnouncement != null) {
            return Response.sendSuccess(200, updatedAnnouncement);
        }
        return Response.sendError(404, "公告不存在！");
    }

    @ApiOperation("删除公告")
    @DeleteMapping("announcement/{id}")
    public Response<Boolean> deleteAnnouncement(@PathVariable("id") @ApiParam("公告ID") Long id) {
        if (announcementService.delete(id)) {
            return Response.sendSuccess(200);
        }
        return Response.sendError(404, "公告不存在！");
    }

}