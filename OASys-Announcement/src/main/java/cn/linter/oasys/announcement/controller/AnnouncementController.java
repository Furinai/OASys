package cn.linter.oasys.announcement.controller;

import cn.linter.oasys.announcement.entity.Announcement;
import cn.linter.oasys.announcement.service.AnnouncementService;
import cn.linter.oasys.common.entity.Response;
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

    @ApiOperation("通过ID查询单个公告")
    @GetMapping("announcement/{id}")
    public Response<Announcement> queryAnnouncement(@PathVariable("id") @ApiParam("公告ID") Long id) {
        return Response.sendSuccess(200, announcementService.query(id));
    }

    @ApiOperation("分页查询所有公告")
    @GetMapping("announcements")
    public Response<List<Announcement>> listAnnouncement(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                                         @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<Announcement> pageInfo = announcementService.list(pageNumber, pageSize);
        return Response.sendSuccess(200, pageInfo.getList(), pageInfo.getTotal());
    }

    @ApiOperation("新增公告")
    @PostMapping("announcement")
    public Response<Announcement> createAnnouncement(@RequestBody @ApiParam("公告") Announcement announcement) {
        return Response.sendSuccess(201, announcementService.create(announcement));
    }

    @ApiOperation("更新公告")
    @PutMapping("announcement")
    public Response<Announcement> updateAnnouncement(@RequestBody @ApiParam("公告") Announcement announcement) {
        return Response.sendSuccess(200, announcementService.update(announcement));
    }

    @ApiOperation("通过ID删除公告")
    @DeleteMapping("announcement/{id}")
    public Response<Boolean> deleteAnnouncement(@PathVariable("id") @ApiParam("公告ID") Long id) {
        return Response.sendSuccess(200, announcementService.delete(id));
    }

}