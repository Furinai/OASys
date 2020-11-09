package cn.linter.oasys.netdisk.service;

import cn.linter.oasys.netdisk.entity.NetFile;
import com.github.pagehelper.PageInfo;

/**
 * 文件服务接口
 *
 * @author wangxiaoyang
 * @since 2020/11/09
 */
public interface NetFileService {

    /**
     * 通过ID查询单个文件
     *
     * @param id 文件ID
     * @return 单个文件
     */
    NetFile query(Long id);

    /**
     * 分页查询所有文件
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 文件列表
     */
    PageInfo<NetFile> list(int pageNumber, int pageSize);

    /**
     * 新增文件
     *
     * @param netFile 文件
     * @return 文件
     */
    NetFile create(NetFile netFile);

    /**
     * 更新文件
     *
     * @param netFile 文件
     * @return 文件
     */
    NetFile update(NetFile netFile);

    /**
     * 通过文件ID删除文件
     *
     * @param id 文件ID
     * @return 是否成功
     */
    boolean delete(Long id);

}