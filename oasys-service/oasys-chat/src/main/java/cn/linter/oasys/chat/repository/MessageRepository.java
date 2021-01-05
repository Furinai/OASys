package cn.linter.oasys.chat.repository;

import cn.linter.oasys.chat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 聊天消息Dao接口
 *
 * @author wangxiaoyang
 * @since 2021/1/5
 */
public interface MessageRepository extends MongoRepository<Message, String> {
}
