package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.user.dao.DeptDao;
import cn.linter.oasys.user.entity.Dept;
import cn.linter.oasys.user.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/15
 */
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptDao deptDao;

    public DeptServiceImpl(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    @Override
    public Dept query(Integer id) {
        return deptDao.select(id);
    }

    @Override
    public PageInfo<Dept> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(deptDao.list());
    }

    @Override
    public Dept create(Dept dept) {
        deptDao.insert(dept);
        return dept;
    }

    @Override
    public Dept update(Dept dept) {
        deptDao.update(dept);
        return query(dept.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return deptDao.delete(id) > 0;
    }

}