package cn.linter.oasys.service;

import cn.linter.oasys.entity.File;
import cn.linter.oasys.entity.User;
import cn.linter.oasys.mapper.FileMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public PageInfo<File> getFiles(int userId, int parentId, boolean personal, int pageNumber, int pageSize) {
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
        LocalDateTime time = LocalDateTime.now();
        folder.setCreatedTime(time);
        fileMapper.insertFile(folder);
    }

    @Override
    public void uploadFile(String fileName, long fileSize, User user, int parentId, boolean personal) {
        File file = new File();
        int index = fileName.lastIndexOf('.');
        file.setName(fileName.substring(0, index));
        file.setPath("/file/" + fileName);
        file.setType(fileName.substring(index + 1));
        file.setSize(formatSize(fileSize));
        file.setParentId(parentId);
        file.setPersonal(personal);
        file.setUser(user);
        LocalDateTime time = LocalDateTime.now();
        file.setCreatedTime(time);
        fileMapper.insertFile(file);
    }

    @Override
    public void renameFile(int id, String newName) {
        fileMapper.updateFile(id, newName);
    }

    @Override
    public void deleteFile(Integer[] ids) {
        String rootPath = new ApplicationHome(getClass()).getSource().getPath();
        for (Integer id : ids) {
            File file = fileMapper.selectFile(id);
            if (!file.getType().equals("文件夹")) {
                String path = rootPath + "/static" + file.getPath();
                java.io.File physicalFile = new java.io.File(path);
                physicalFile.delete();
            }
        }
        fileMapper.deleteFile(ids);
    }

    public String formatSize(long size) {
        if (size < 1024) {
            return size + "B";
        } else if (size < 1048576) {
            return (size >>= 10) + "KB";
        } else {
            return (size >>= 20) + "MB";
        }
    }
}
