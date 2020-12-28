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
    Announcement selectById(Long id);

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
    List<Announcement> list();

    /**
     * 新增公告
     *
     * @param announcement 公告实例
     * @return 影响行数
     */
    int insert(Announcement announcement);

    /**
     * 更新公告
     *
     * @param announcement 公告实例
     * @return 影响行数
     */
    int update(Announcement announcement);

    /**
     * 通过ID删除单个公告
     *
     * @param id 公告ID
     * @return 影响行数
     */
    int deleteById(Long id);

}