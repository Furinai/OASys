package cn.linter.oasys.announcement.dao;

import cn.linter.oasys.announcement.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公告数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@Mapper
public interface AnnouncementDao {

    /**
     * 通过ID查询单个公告
     *
     * @param id 公告ID
     * @return 单个公告
     */
    Announcement select(Long id);

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
    List<Announcement> list();

    /**
     * 通过公告实体查询所有公告
     *
     * @param announcement 公告
     * @return 公告列表
     */
    List<Announcement> listByEntity(Announcement announcement);

    /**
     * 新增公告
     *
     * @param announcement 公告
     * @return 公告
     */
    int insert(Announcement announcement);

    /**
     * 更新公告
     *
     * @param announcement 公告
     * @return 公告
     */
    int update(Announcement announcement);

    /**
     * 通过公告ID删除公告
     *
     * @param id 公告ID
     * @return 是否成功
     */
    int delete(Long id);

}