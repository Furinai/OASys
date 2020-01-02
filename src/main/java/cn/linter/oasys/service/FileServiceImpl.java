package cn.linter.oasys.service;

import cn.linter.oasys.mapper.FileMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public PageInfo<?> getFiles(int userId, int parentId, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<>(fileMapper.selectFiles(userId, parentId));
    }
}
