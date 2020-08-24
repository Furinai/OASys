package cn.linter.oasys.mapper;

import cn.linter.oasys.entity.NetFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NetFileMapper {
    List<NetFile> selectNetFiles(@Param("userId") int userId, @Param("parentId") int parentId, @Param("personal") boolean personal);

    NetFile selectNetFile(@Param("id") int id);

    void insertNetFile(NetFile netFile);

    void updateNetFile(@Param("id") int id, @Param("newName") String newName);

    void deleteNetFiles(@Param("ids") Integer[] ids);
}