package cn.linter.oasys.file.dao;

import cn.linter.oasys.file.entity.File;
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
    File selectById(Long id);

    /**
     * 通过文件实体查询所有文件
     *
     * @param file 文件实体
     * @return 文件列表
     */
    List<File> listByEntity(File file);

    /**
     * 新增文件
     *
     * @param file 文件实例
     * @return 影响行数
     */
    int insert(File file);

    /**
     * 更新文件
     *
     * @param file 文件实例
     * @return 影响行数
     */
    int update(File file);

    /**
     * 通过ID删除文件
     *
     * @param id 文件ID
     * @return 影响行数
     */
    int deleteById(Long id);

}