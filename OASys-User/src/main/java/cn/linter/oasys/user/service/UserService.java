package cn.linter.oasys.user.service;

import cn.linter.oasys.user.entity.User;
import com.github.pagehelper.PageInfo;

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
    User getById(Long id);

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    User getByUsername(String username);

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    PageInfo<User> getAll(int pageNumber, int pageSize);

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户
     */
    User add(User user);

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 用户
     */
    User update(User user);

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteById(Long id);

}