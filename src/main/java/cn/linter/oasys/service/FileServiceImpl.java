package cn.linter.oasys.service;

import cn.linter.oasys.entity.File;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.mapper.FileMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public PageInfo<?> getFiles(int userId, int parentId, boolean personal, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(fileMapper.selectFiles(userId, parentId, personal));
    }

    @Override
    public void addFolder(String folderName, int userId, int parentId, boolean personal) {
        File folder = new File();
        User user = new User();
        user.setId(userId);
        folder.setName(folderName);
        folder.setPath("/");
        folder.setSize("-");
        folder.setType("文件夹");
        folder.setUser(user);
        folder.setParentId(parentId);
        folder.setPersonal(personal);
        long time = System.currentTimeMillis();
        folder.setCreateTime(new Timestamp(time));
        fileMapper.insertFolder(folder);
    }
}
