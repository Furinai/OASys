package cn.linter.oasys.information.service.impl;

import cn.linter.oasys.information.dao.AnnouncementDao;
import cn.linter.oasys.information.entity.Announcement;
import cn.linter.oasys.information.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AnnouncementDao announcementDao;

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
        if (announcementDao.update(announcement) > 0) {
            return announcementDao.select(announcement.getId());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return announcementDao.delete(id) > 0;
    }

}