package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    List<File> selectFiles(@Param("userId") int userId, @Param("parentId") int parentId, @Param("personal") boolean personal);

    void insertFolder(File folder);
}