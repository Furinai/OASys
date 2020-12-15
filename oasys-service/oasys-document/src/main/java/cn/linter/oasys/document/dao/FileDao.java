package cn.linter.oasys.document.dao;

import cn.linter.oasys.document.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件数据库访问层
 *
 * @author wangxiaoyang
 * @since 2020/11/10
 */
@Mapper
public interface FileDao {

    /**
     * 通过ID查询单个文件
     *
     * @param id 文件ID
     * @return 单个文件
     */
    File select(Long id);

    /**
     * 通过文件实例查询所有文件
     *
     * @param file 文件实例
     * @return 文件列表
     */
    List<File> list(File file);

    /**
     * 新增文件
     *
     * @param file 文件实例
     * @return 影响的行数
     */
    int insert(File file);

    /**
     * 更新文件
     *
     * @param file 文件实例
     * @return 影响的行数
     */
    int update(File file);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 影响的行数
     */
    int delete(Long id);

}