package cn.linter.oasys.announcement.service;

import cn.linter.oasys.announcement.entity.Announcement;

import java.util.List;

/**
 * 公告服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
public interface AnnouncementService {

    /**
     * 通过ID查询单个公告
     *
     * @param id 公告ID
     * @return 单个公告
     */
    Announcement query(Long id);

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
   List<Announcement> list();

    /**
     * 新增公告
     *
     * @param announcement 公告
     * @return 公告
     */
    Announcement create(Announcement announcement);

    /**
     * 更新公告
     *
     * @param announcement 公告
     * @return 公告
     */
    Announcement update(Announcement announcement);

    /**
     * 通过公告ID删除公告
     *
     * @param id 公告ID
     * @return 是否成功
     */
    boolean delete(Long id);

}