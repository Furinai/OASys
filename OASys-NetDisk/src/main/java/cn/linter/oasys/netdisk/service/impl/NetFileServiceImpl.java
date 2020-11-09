package cn.linter.oasys.netdisk.service.impl;

import cn.linter.oasys.netdisk.dao.NetFileDao;
import cn.linter.oasys.netdisk.entity.NetFile;
import cn.linter.oasys.netdisk.service.NetFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件服务实现类
 *
 * @author wangxiaoyang
 * @since 2020/11/09
 */
@Service
public class NetFileServiceImpl implements NetFileService {

    @Autowired
    private NetFileDao netFileDao;

    /**
     * 通过ID查询单个文件
     *
     * @param id 文件ID
     * @return 单个文件
     */
    @Override
    public NetFile query(Long id) {
        return netFileDao.select(id);
    }

    /**
     * 分页查询所有文件
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 文件列表
     */
    @Override
    public PageInfo<NetFile> list(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return PageInfo.of(netFileDao.list());
    }

    /**
     * 新增文件
     *
     * @param netFile 文件
     * @return 文件
     */
    @Override
    public NetFile create(NetFile netFile) {
        netFileDao.insert(netFile);
        return netFile;
    }

    /**
     * 更新文件
     *
     * @param netFile 文件
     * @return 文件
     */
    @Override
    public NetFile update(NetFile netFile) {
        netFileDao.update(netFile);
        return query(netFile.getId());
    }

    /**
     * 通过文件ID删除文件
     *
     * @param id 文件ID
     * @return 是否成功
     */
    @Override
    public boolean delete(Long id) {
        return netFileDao.delete(id) > 0;
    }

}