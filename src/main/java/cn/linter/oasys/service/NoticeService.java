package cn.linter.oasys.service;

import cn.linter.oasys.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNotices(String username);

    void sendNotice(String username, String message);

    void markRead(Integer[] ids);
}
