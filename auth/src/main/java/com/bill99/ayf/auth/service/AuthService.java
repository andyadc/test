package com.bill99.ayf.auth.service;

import com.bill99.ayf.auth.dto.OrgDTO;
import com.bill99.ayf.auth.dto.PermissionDTO;
import com.bill99.ayf.auth.dto.PermissionTree;
import com.bill99.ayf.auth.dto.RoleDTO;
import com.bill99.ayf.auth.dto.UserDTO;
import com.bill99.ayf.auth.persistence.entity.Org;
import com.bill99.ayf.auth.persistence.entity.Permission;
import com.bill99.ayf.auth.persistence.entity.Role;
import com.bill99.ayf.auth.persistence.entity.User;
import com.bill99.ayf.auth.persistence.mapper.OrgMapper;
import com.bill99.ayf.auth.persistence.mapper.PermissionMapper;
import com.bill99.ayf.auth.persistence.mapper.RoleMapper;
import com.bill99.ayf.auth.persistence.mapper.UserMapper;
import com.bill99.ayf.auth.persistence.model.PermissionQuery;
import com.bill99.ayf.auth.persistence.model.RolePermission;
import com.bill99.ayf.auth.persistence.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * andy.an
 * 2020/3/25
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户列表
     */
    public List<User> listUser() {
        return userMapper.selectList();
    }

    /**
     * 角色列表
     */
    public List<Role> listRole() {
        return roleMapper.selectList();
    }

    /**
     * 添加用户
     */
    public void addUser(UserDTO dto) {
        Assert.notNull(dto.getName(), "User name is null");

        User user = new User();
        user.setName(dto.getName());
        user.setOrgId(dto.getOrgId());
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);

        if (dto.getRoleIds() != null && dto.getRoleIds().size() > 0) {
            dto.setId(user.getId());
            addUserRoles(dto);
        }
    }

    /**
     * 添加用户的角色
     */
    public void addUserRoles(UserDTO dto) {
        Assert.notNull(dto.getId(), "User id is null");
        Assert.notNull(dto.getRoleIds(), "User role ids is null");

        Long userId = dto.getId();
        roleMapper.deleteUserRoles(userId);

        List<Long> roleIds = dto.getRoleIds();
        List<UserRole> userRoleList = new ArrayList<>(roleIds.size());
        roleIds.forEach(r -> userRoleList.add(new UserRole(userId, r)));
        roleMapper.batchSaveUserRoles(userRoleList);
    }

    /**
     * 添加角色的权限
     */
    public void addRolePermissions(RoleDTO dto) {
        Assert.notNull(dto.getId(), "Role id is null");
        Assert.notNull(dto.getPermissionIds(), "Role permission ids is null");

        Long roleId = dto.getId();
        roleMapper.deleteRolePermissions(roleId);

        List<Long> permissionIds = dto.getPermissionIds();
        List<RolePermission> rolePermissionList = new ArrayList<>(permissionIds.size());
        permissionIds.forEach(p -> rolePermissionList.add(new RolePermission(roleId, p)));
        roleMapper.batchSaveRolePermissions(rolePermissionList);
    }

    /**
     * 添加角色信息
     */
    public void addRole(RoleDTO dto) {
        Assert.notNull(dto.getName(), "Role name is null");

        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        roleMapper.insertSelective(role);
    }

    /**
     * 更新角色信息
     */
    public void updateRole(RoleDTO dto) {
        Assert.notNull(dto.getId(), "Role id is null");

        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 删除角色信息
     */
    public void deleteRole(RoleDTO dto) {
        Long id = dto.getId();
        Assert.notNull(id, "Role id is null");

        int count = roleMapper.checkRoleUsed(id);
        if (count == 0) {
            roleMapper.deleteByPrimaryKey(id);
            return;
        }
        //TODO
        throw new RuntimeException("Role [" + id + "-" + dto.getName() + "] already in use");
    }

    /**
     * 保存组织信息
     */
    public void addOrg(OrgDTO dto) {
        Assert.notNull(dto.getName(), "Org name is null");

        Org org = new Org();
        org.setName(dto.getName());
        org.setCode(dto.getCode());
        org.setSuperviseId(dto.getSuperviseId()); // 如果有
        org.setCreateTime(new Date());
        org.setUpdateTime(new Date());
        orgMapper.insertSelective(org);
    }

    /**
     * 更新组织信息
     */
    public void updateOrg(OrgDTO dto) {
        Assert.notNull(dto.getId(), "Org id is null");

        Org org = new Org();
        BeanUtils.copyProperties(dto, org);
        org.setUpdateTime(new Date());

        orgMapper.updateByPrimaryKeySelective(org);
    }

    /**
     * 新增权限信息
     */
    public void addPermission(PermissionDTO dto) {
        Assert.notNull(dto.getName(), "Permission name is null");

        Permission permission = new Permission();
        BeanUtils.copyProperties(dto, permission);
        permission.setDeleted(0);
        permission.setCreateTime(new Date());
        permission.setUpdateTime(new Date());
        permissionMapper.insertSelective(permission);
    }

    /**
     * 更新权限信息
     */
    public void updatePermission(PermissionDTO dto) {
        Assert.notNull(dto.getId(), "Permission id is null");

        Permission permission = new Permission();
        BeanUtils.copyProperties(dto, permission);
        permission.setUpdateTime(new Date());

        permissionMapper.updateByPrimaryKey(permission);
    }

    /**
     * 删除权限信息
     */
    public void deletePermission(PermissionDTO dto) {
        Long id = dto.getId();
        Assert.notNull(id, "Permission id is null");

        int count = roleMapper.checkPermissionUsed(id);
        if (count == 0) {
            permissionMapper.deleteByPrimaryKey(id);
            return;
        }
        //TODO
        throw new RuntimeException("Permission [" + id + "-" + dto.getName() + "] already in use");
    }
    
    public boolean checkUserPermission(Long userId, String permission) {
        PermissionQuery query = new PermissionQuery(userId);
        query.setPermission(permission);
        return permissionMapper.checkUserPermission(query) > 0;
    }

    /**
     * 查询所有权限树, 并显示是否选中
     */
    @Transactional(readOnly = true)
    public List<PermissionTree> findCheckedPermissions(String sysCode, Long userId) {
        List<PermissionTree> all = this.findPermissions(sysCode);

        PermissionQuery query = new PermissionQuery(userId, sysCode);
        List<Permission> ups = permissionMapper.selectPermissions(query);
        if (ups != null && ups.size() > 0) {
            List<Long> ids = ups.stream().map(Permission::getId).collect(Collectors.toList());
            setChecked(all, ids);
        }
        return all;
    }

    private void setChecked(List<PermissionTree> all, List<Long> ids) {
        for (PermissionTree tree : all) {
            tree.setChecked(ids.contains(tree.getId()));
            List<PermissionTree> children = tree.getChildren();
            if (children != null && children.size() > 0) {
                setChecked(children, ids);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<PermissionTree> findPermissions(String sysCode) {
        PermissionQuery query = new PermissionQuery(sysCode);
        return this.findPermissionsTree(query);
    }

    @Transactional(readOnly = true)
    public List<PermissionTree> findPermissions(String sysCode, Long userId) {
        PermissionQuery query = new PermissionQuery(userId, sysCode);
        return this.findPermissionsTree(query);
    }

    private List<PermissionTree> findPermissionsTree(PermissionQuery query) {
        List<Permission> sourceList = permissionMapper.selectPermissions(query);

        List<PermissionTree> trees = new ArrayList<>(sourceList.size());
        sourceList.forEach(p -> trees.add(toTree(p)));

        List<PermissionTree> permissionTreeList = new ArrayList<>(sourceList.size());
        for (PermissionTree p : trees) {
            if (p.getPid() == 0L) { // 0:根节点
                permissionTreeList.add(getChildren(p, trees));
            }
        }

        return permissionTreeList;
    }

    private PermissionTree getChildren(PermissionTree p, List<PermissionTree> sources) {
        List<PermissionTree> children = new ArrayList<>();
        for (PermissionTree tree : sources) {
            if (tree.getPid().equals(p.getId())) {
                children.add(getChildren(tree, sources));
            }
        }
        p.setChildren(children);
        return p;
    }

    private static PermissionTree toTree(Permission permission) {
        PermissionTree tree = new PermissionTree();
        tree.setPid(permission.getPid());
        tree.setId(permission.getId());
        tree.setName(permission.getName());
        tree.setPath(permission.getPath());
        tree.setPermission(permission.getPermission());
        return tree;
    }

}
