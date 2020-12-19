package cn.linter.oasys.information.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.information.entity.Announcement;
import cn.linter.oasys.information.service.AnnouncementService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Page<Announcement>> listAnnouncement(@RequestParam(defaultValue = "1") @ApiParam("页号") int pageNumber,
                                                       @RequestParam(defaultValue = "10") @ApiParam("页大小") int pageSize) {
        PageInfo<Announcement> pageInfo = announcementService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @ApiOperation("新增公告")
    @PostMapping("announcement")
    public Result<Announcement> createAnnouncement(@RequestBody @ApiParam("公告实例") Announcement announcement) {
        return Result.of(ResultStatus.SUCCESS, announcementService.create(announcement));
    }

    @ApiOperation("更新公告")
    @PutMapping("announcement")
    public Result<Announcement> updateAnnouncement(@RequestBody @ApiParam("公告实例") Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.update(announcement);
        return Result.of(ResultStatus.SUCCESS, updatedAnnouncement);
    }

    @ApiOperation("删除公告")
    @DeleteMapping("announcement/{id}")
    public Result<String> deleteAnnouncement(@PathVariable("id") @ApiParam("公告ID") Long id) {
        announcementService.delete(id);
        return Result.of(ResultStatus.SUCCESS, "删除成功！");
    }

}