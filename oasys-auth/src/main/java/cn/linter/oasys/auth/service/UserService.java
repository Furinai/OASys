package cn.linter.oasys.auth.service;

import cn.linter.oasys.auth.entity.User;

/**
 * 用户服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
public interface UserService {

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    User query(Long id);

}