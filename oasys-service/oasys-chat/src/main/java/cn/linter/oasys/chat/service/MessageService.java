package cn.linter.oasys.chat.service;

import cn.linter.oasys.chat.entity.Message;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

/**
 * 聊天消息服务接口
 *
 * @author wangxiaoyang
 * @since 2021/1/5
 */
public interface MessageService {

    /**
     * 通过时间范围分页查询聊天记录
     *
     * @param start      开始时间
     * @param end        结束时间
     * @param pageNumber 页数
     * @param pageSize   页大小
     * @return 分页实体
     */
    Page<Message> listMessage(LocalDateTime start, LocalDateTime end, int pageNumber, int pageSize);

}
