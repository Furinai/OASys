package cn.linter.oasys.service;

import cn.linter.oasys.entity.User;
import com.github.pagehelper.PageInfo;

public interface FileService {
    PageInfo<?> getFiles(int userId, int parentId, boolean personal, int pageNumber, int pageSize);

    void addFolder(String folderName, int userId, int parentId, boolean personal);

    void uploadFile(String fileName, long fileSize, User user, int parentId, boolean personal);

    void renameFile(int id, String newName);

    void deleteFile(Integer[] ids);
}
