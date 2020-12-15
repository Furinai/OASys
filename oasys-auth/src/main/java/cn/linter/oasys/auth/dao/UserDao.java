package cn.linter.oasys.auth.dao;

import cn.linter.oasys.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    User select(Long id);

}