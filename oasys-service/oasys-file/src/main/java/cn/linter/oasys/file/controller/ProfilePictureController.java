package cn.linter.oasys.file.controller;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.file.service.ProfilePictureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 头像控制器
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
@RestController
@RequestMapping("profile-pictures")
public class ProfilePictureController {

    private final ProfilePictureService profilePictureService;

    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @PostMapping
    public Result<String> uploadFile(@RequestParam MultipartFile multipartFile) throws Exception {
        return Result.of(ResultStatus.SUCCESS, profilePictureService.createProfilePicture(multipartFile));
    }

}
