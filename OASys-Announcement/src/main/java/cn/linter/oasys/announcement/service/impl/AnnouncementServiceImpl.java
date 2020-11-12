package cn.linter.oasys.announcement.service.impl;

import cn.linter.oasys.announcement.dao.AnnouncementDao;
import cn.linter.oasys.announcement.entity.Announcement;
import cn.linter.oasys.announcement.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公告服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/11
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public Announcement query(Long id) {
        return announcementDao.select(id);
    }

    @Override
    public PageInfo<Announcement> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(announcementDao.list());
    }

    @Override
    public Announcement create(Announcement announcement) {
        announcementDao.insert(announcement);
        return announcement;
    }

    @Override
    public Announcement update(Announcement announcement) {
        announcementDao.update(announcement);
        return query(announcement.getId());
    }

    @Override
    public boolean delete(Long id) {
        return announcementDao.delete(id) > 0;
    }

}