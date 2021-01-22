package cn.linter.oasys.gateway.dto;

import cn.linter.oasys.gateway.entity.Permission;
import cn.linter.oasys.gateway.entity.Role;
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
