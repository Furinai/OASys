package cn.linter.oasys.auth.service.impl;

import cn.linter.oasys.auth.dao.UserDao;
import cn.linter.oasys.auth.entity.User;
import cn.linter.oasys.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public User queryById(Long id) {
        return userDao.selectById(id);
    }

}