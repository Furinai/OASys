package cn.linter.oasys.announcement.service;

import cn.linter.oasys.announcement.entity.Announcement;
import com.github.pagehelper.PageInfo;

/**
 * 公告服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
public interface AnnouncementService {

    /**
     * 分页查询所有公告
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 公告列表
     */
    PageInfo<Announcement> list(int pageNumber, int pageSize);

    /**
     * 新增公告
     *
     * @param announcement 公告实例
     * @return 公告实例
     */
    Announcement create(Announcement announcement);

    /**
     * 更新公告
     *
     * @param announcement 公告实例
     * @return 公告实例
     */
    Announcement update(Announcement announcement);

    /**
     * 删除公告
     *
     * @param id 公告ID
     */
    void delete(Long id);

}