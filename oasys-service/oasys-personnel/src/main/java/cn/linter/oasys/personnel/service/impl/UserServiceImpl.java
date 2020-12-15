package cn.linter.oasys.personnel.service.impl;

import cn.linter.oasys.personnel.dao.UserDao;
import cn.linter.oasys.personnel.entity.User;
import cn.linter.oasys.personnel.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 通过ID查询单个用户
     *
     * @param id 用户ID
     * @return 单个用户
     */
    @Override
    public User query(Long id) {
        return userDao.select(id);
    }

    /**
     * 分页查询所有用户
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 用户列表
     */
    @Override
    public PageInfo<User> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(userDao.list());
    }

    /**
     * 新增用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    @Override
    public User create(User user) {
        //todo 处理重复用户名
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userDao.insert(user);
        return user;
    }

    /**
     * 更新用户
     *
     * @param user 用户实例
     * @return 用户实例
     */
    @Override
    public User update(User user) {
        //todo 处理重复用户名
        String rawPassword = user.getPassword();
        if (rawPassword != null) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
        user.setUpdateTime(LocalDateTime.now());
        if (userDao.update(user) > 0) {
            return query(user.getId());
        }
        return null;
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @Override
    public boolean delete(Long id) {
        return userDao.delete(id) > 0;
    }

}