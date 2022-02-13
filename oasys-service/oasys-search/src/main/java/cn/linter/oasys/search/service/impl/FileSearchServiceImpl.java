package cn.linter.oasys.search.service.impl;

import cn.linter.oasys.search.entity.File;
import cn.linter.oasys.search.repository.FileSearchRepository;
import cn.linter.oasys.search.service.FileSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 文件搜索服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/1/10
 */
@Service
public class FileSearchServiceImpl implements FileSearchService {

    private final FileSearchRepository fileSearchRepository;

    public FileSearchServiceImpl(FileSearchRepository fileSearchRepository) {
        this.fileSearchRepository = fileSearchRepository;
    }

    @Override
    public void saveFile(File file) {
        fileSearchRepository.save(file);
    }

    @Override
    public void deleteFileById(Long id) {
        fileSearchRepository.deleteById(id);
    }

    @Override
    public Page<File> findAllByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return fileSearchRepository.findAllByName(name, pageable);
    }

}
