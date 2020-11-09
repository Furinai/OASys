package cn.linter.oasys.netdisk.dao;

import cn.linter.oasys.netdisk.entity.NetFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/09
 */
@Mapper
public interface NetFileDao {

    /**
     * 通过ID查询单个文件
     *
     * @param id 文件ID
     * @return 单个文件
     */
    NetFile select(Long id);

    /**
     * 查询所有文件
     *
     * @return 文件列表
     */
    List<NetFile> list();

    /**
     * 通过文件实体查询所有文件
     *
     * @param netFile 文件
     * @return 文件列表
     */
    List<NetFile> listByEntity(NetFile netFile);

    /**
     * 新增文件
     *
     * @param netFile 文件
     * @return 文件
     */
    int insert(NetFile netFile);

    /**
     * 更新文件
     *
     * @param netFile 文件
     * @return 文件
     */
    int update(NetFile netFile);

    /**
     * 通过文件ID删除文件
     *
     * @param id 文件ID
     * @return 是否成功
     */
    int delete(Long id);

}