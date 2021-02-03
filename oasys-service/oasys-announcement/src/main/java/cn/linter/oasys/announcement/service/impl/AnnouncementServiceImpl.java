package cn.linter.oasys.announcement.service.impl;

import cn.linter.oasys.announcement.dao.AnnouncementDao;
import cn.linter.oasys.announcement.entity.Announcement;
import cn.linter.oasys.announcement.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 公告服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDao announcementDao;

    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public PageInfo<Announcement> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(announcementDao.list());
    }

    @Override
    public Announcement create(Announcement announcement) {
        LocalDateTime now = LocalDateTime.now();
        announcement.setCreateTime(now);
        announcement.setUpdateTime(now);
        announcementDao.insert(announcement);
        return announcement;
    }

    @Override
    public Announcement update(Announcement announcement) {
        announcement.setUpdateTime(LocalDateTime.now());
        announcementDao.update(announcement);
        return announcementDao.selectById(announcement.getId());
    }

    @Override
    public void delete(Long id) {
        announcementDao.deleteById(id);
    }

}