package cn.linter.oasys.user.service.impl;

import cn.linter.oasys.user.dao.PermissionDao;
import cn.linter.oasys.user.dto.PermissionRoleDTO;
import cn.linter.oasys.user.entity.Permission;
import cn.linter.oasys.user.service.PermissionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限服务实现类
 *
 * @author wangxiaoyang
 * @since 2021/01/14
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public Permission queryById(Integer id) {
        return permissionDao.selectById(id);
    }

    @Override
    public List<Permission> list(boolean treeMode) {
        List<Permission> permissions = permissionDao.list();
        if (treeMode) {
            return convertListToTree(permissions);
        }
        return permissions;
    }

    @Override
    public List<Permission> listByUsername(String username, boolean treeMode) {
        List<Permission> permissions = permissionDao.listByUsername(username);
        if (treeMode) {
            return convertListToTree(permissions);
        }
        return permissions;
    }

    @Override
    public List<Permission> listByRoleId(Integer roleId, boolean treeMode) {
        List<Permission> permissions = permissionDao.listByRoleId(roleId);
        if (treeMode) {
            return convertListToTree(permissions);
        }
        return permissions;
    }

    @Cacheable(value = "permission-role", key = "#type.toString()")
    @Override
    public List<PermissionRoleDTO> listRoleByType(Permission.Type type) {
        return permissionDao.listRoleByType(type);
    }

    @Override
    public Permission create(Permission permission) {
        LocalDateTime now = LocalDateTime.now();
        permission.setCreateTime(now);
        permission.setUpdateTime(now);
        permissionDao.insert(permission);
        return permission;
    }

    @Override
    public Permission update(Permission permission) {
        permission.setUpdateTime(LocalDateTime.now());
        permissionDao.update(permission);
        return queryById(permission.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return permissionDao.delete(id) > 0;
    }

    @Override
    public List<Permission> convertListToTree(List<Permission> permissions) {
        Map<Integer, Permission> map = new HashMap<>();
        List<Permission> result = new ArrayList<>();
        for (Permission permission : permissions) {
            map.put(permission.getId(), permission);
        }
        for (Permission current : permissions) {
            int parentId = current.getParentId();
            if (parentId == 0) {
                result.add(current);
            } else {
                Permission parent = map.get(parentId);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(current);
                }
            }
        }
        return result;
    }

    @Override
    public List<Permission> convertTreeToList(List<Permission> permissions) {
        List<Permission> result = new ArrayList<>();
        for (Permission permission : permissions) {
            List<Permission> children = permission.getChildren();
            if (children != null && children.size() > 0) {
                for (Permission second : children) {
                    result.add(second);
                    List<Permission> third = second.getChildren();
                    if (third != null && third.size() > 0) {
                        result.addAll(third);
                    }
                }
            }
        }
        return result;
    }

}