package cn.linter.oasys.user.service.impl;


import cn.linter.oasys.common.entity.User;
import cn.linter.oasys.user.dao.UserDao;
import cn.linter.oasys.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    @Override
    public User getById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 通过用户名查询单个用户
     *
     * @param username 用户名
     * @return 单个用户
     */
    @Override
    public User getByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    @Override
    public PageInfo<User> getAll(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(userDao.selectAll());
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public User add(User user) {
        userDao.insert(user);
        return user;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public User update(User user) {
        userDao.update(user);
        return getById(user.getId());
    }

    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id) > 0;
    }

}