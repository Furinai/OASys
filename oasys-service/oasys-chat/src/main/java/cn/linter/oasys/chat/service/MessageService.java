package cn.linter.oasys.chat.service;

import cn.linter.oasys.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param start    开始时间
     * @param end      结束时间
     * @param pageable 分页条件
     * @return 分页实体
     */
    Page<Message> listMessage(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
