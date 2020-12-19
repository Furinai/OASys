package cn.linter.oasys.document.controller;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.document.service.ProfilePictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 头像控制器
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
@Api(tags = "头像控制器")
@RestController
public class ProfilePictureController {

    @Autowired
    private ProfilePictureService profilePictureService;

    @ApiOperation("上传头像")
    @PostMapping("profile-picture")
    public Result<String> uploadFile(@ApiParam("二进制文件") MultipartFile multipartFile) throws Exception {
        return Result.of(ResultStatus.SUCCESS, profilePictureService.uploadProfilePicture(multipartFile));
    }

}
