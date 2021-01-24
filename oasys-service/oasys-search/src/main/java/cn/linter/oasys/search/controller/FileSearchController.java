package cn.linter.oasys.search.controller;

import cn.linter.oasys.common.entity.Page;
import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.search.entity.File;
import cn.linter.oasys.search.service.FileSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件搜索控制器
 *
 * @author wangxiaoyang
 * @since 2021/1/10
 */
@RestController
@RequestMapping("searches/files")
public class FileSearchController {

    private final FileSearchService fileSearchService;

    public FileSearchController(FileSearchService fileSearchService) {
        this.fileSearchService = fileSearchService;
    }

    @RequestMapping
    public Result<Page<File>> queryFile(@RequestParam String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        org.springframework.data.domain.Page<File> files = fileSearchService.findAllByName(name, pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(files.getContent(), files.getTotalElements()));
    }

}
