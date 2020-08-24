package cn.linter.oasys.service;

import cn.linter.oasys.entity.NetFile;
import cn.linter.oasys.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface NetFileService {
    PageInfo<NetFile> getNetFiles(int userId, int parentId, boolean personal, int pageNumber, int pageSize);

    void addFolder(String folderName, User user, int parentId, boolean personal);

    void uploadFile(MultipartFile multipartFile, User user, int parentId, boolean personal);

    void renameNetFile(int id, String newName);

    void deleteNetFiles(Integer[] ids);
}
