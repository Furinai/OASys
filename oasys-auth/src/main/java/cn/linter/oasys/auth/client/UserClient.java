package cn.linter.oasys.auth.client;

import cn.linter.oasys.auth.entity.Permission;
import cn.linter.oasys.auth.entity.Role;
import cn.linter.oasys.auth.entity.User;
import cn.linter.oasys.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author wangxiaoyang
 * @date 2020/12/20
 */
@FeignClient(name = "user-service")
public interface UserClient {

    /**
     * 查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @GetMapping("users/{username}")
    Result<User> queryUser(@PathVariable String username);

    /**
     * 查询用户的角色
     *
     * @param username 用户名
     * @return 角色列表
     */
    @GetMapping("users/{username}/roles")
    Result<List<Role>> listRoleOfUser(@PathVariable String username);

    /**
     * 查询用户的权限
     *
     * @param username 用户名
     * @param treeMode 是否树形
     * @return 权限集合
     */
    @GetMapping("users/{username}/permissions")
    Result<List<Permission>> listPermissionOfUser(@PathVariable String username, @RequestParam(defaultValue = "false") boolean treeMode);

}
