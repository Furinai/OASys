package cn.linter.oasys.user.dto;

import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.entity.Role;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 权限和角色映射数据传输对象
 *
 * @author wangxiaoyang
 * @since 2021/1/21
 */
@Data
@ToString
public class PermissionRoleDTO {

    private Permission permission;

    private List<Role> roles;

}
