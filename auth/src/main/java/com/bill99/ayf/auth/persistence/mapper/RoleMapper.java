package com.bill99.ayf.auth.persistence.mapper;

import com.bill99.ayf.auth.persistence.entity.Role;
import com.bill99.ayf.auth.persistence.model.RolePermission;
import com.bill99.ayf.auth.persistence.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    int deleteUserRoles(Long userId);
    
    int batchSaveUserRoles(List<UserRole> ur);
    
    int deleteRolePermissions(Long roleId);
    
    int batchSaveRolePermissions(List<RolePermission> rp);

    int checkRoleUsed(Long roleId);

    int checkPermissionUsed(Long permissionId);
    
    List<Role> selectList();
    
}