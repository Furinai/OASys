package cn.linter.oasys.service;

import com.github.pagehelper.PageInfo;

public interface FileService {
    PageInfo<?> getFiles(int userId, int parentId, boolean personal, int pageNumber, int pageSize);

    void addFolder(String folderName, int userId, int parentId, boolean personal);
}
