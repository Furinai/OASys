package cn.linter.oasys.chat.repository;

import cn.linter.oasys.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * 聊天消息数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/1/5
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * 通过时间范围分页查询聊天记录
     *
     * @param start    开始时间
     * @param end      结束时间
     * @param pageable 分页条件
     * @return 分页实体
     */
    Page<Message> findAllByCreateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    /**
     * 通过时间范围分页查询聊天记录
     *
     * @param end      结束时间
     * @param pageable 分页条件
     * @return 分页实体
     */
    Page<Message> findAllByCreateTimeBefore(LocalDateTime end, Pageable pageable);

    /**
     * 通过时间范围分页查询聊天记录
     *
     * @param start    开始时间
     * @param pageable 分页条件
     * @return 分页实体
     */
    Page<Message> findAllByCreateTimeAfter(LocalDateTime start, Pageable pageable);

}
