package cn.linter.oasys.service;

import com.github.pagehelper.PageInfo;

public interface FileService {
    PageInfo<?> getFiles(int userId, int parentId, int pageNumber, int pageSize);
}
