package cn.linter.oasys.netdisk.dao;

import cn.linter.oasys.netdisk.entity.NetFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/10
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
     * @param netFile 文件实例
     * @return 文件列表
     */
    List<NetFile> list(NetFile netFile);

    /**
     * 新增文件
     *
     * @param netFile 文件实例
     * @return 文件实例
     */
    int insert(NetFile netFile);

    /**
     * 更新文件
     *
     * @param netFile 文件实例
     * @return 影响的行数
     */
    int update(NetFile netFile);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 影响的行数
     */
    int delete(Long id);

}