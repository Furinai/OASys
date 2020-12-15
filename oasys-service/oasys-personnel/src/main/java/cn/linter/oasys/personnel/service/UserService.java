package cn.linter.oasys.personnel.service;

import cn.linter.oasys.personnel.entity.User;
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
    User query(Long id);

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    PageInfo<User> list(int pageNumber, int pageSize);

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User create(User user);

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    User update(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    boolean delete(Long id);

}