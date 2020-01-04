package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    List<File> selectFiles(@Param("userId") int userId, @Param("parentId") int parentId, @Param("personal") boolean personal);

    File selectFile(@Param("id") int id);

    void insertFile(File file);

    void updateFile(@Param("id") int id, @Param("newName") String newName);

    void deleteFile(@Param("ids") Integer[] ids);
}