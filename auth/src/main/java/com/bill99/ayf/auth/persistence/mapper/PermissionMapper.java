package com.bill99.ayf.auth.persistence.mapper;

import com.bill99.ayf.auth.persistence.entity.Permission;
import com.bill99.ayf.auth.persistence.model.PermissionQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectPermissions(PermissionQuery query);
    
    int checkUserPermission(PermissionQuery query);
}