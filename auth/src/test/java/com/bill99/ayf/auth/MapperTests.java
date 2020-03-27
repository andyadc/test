package com.bill99.ayf.auth;

import com.bill99.ayf.auth.persistence.entity.Permission;
import com.bill99.ayf.auth.persistence.mapper.OrgMapper;
import com.bill99.ayf.auth.persistence.mapper.PermissionMapper;
import com.bill99.ayf.auth.persistence.mapper.RoleMapper;
import com.bill99.ayf.auth.persistence.mapper.UserMapper;
import com.bill99.ayf.auth.persistence.model.PermissionQuery;
import com.bill99.ayf.auth.persistence.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * andy.an
 * 2020/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {

    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapper() {
        System.out.println(orgMapper);
        System.out.println(permissionMapper);
        System.out.println(roleMapper);
        System.out.println(userMapper);
    }

    @Test
    public void testPermission() {
        List<Permission> permissionList = permissionMapper.selectPermissions(new PermissionQuery(1L));
        permissionList.forEach(p -> System.out.println(p.getId() + " - " + p.getPermission()));
    }

    @Test
    public void testRole() {
        UserRole ur1 = new UserRole(11L, 21L);
        UserRole ur2 = new UserRole(11L, 22L);
        UserRole ur3 = new UserRole(11L, 23L);
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(ur1);
        userRoles.add(ur2);
        userRoles.add(ur3);

        roleMapper.batchSaveUserRoles(userRoles);
    }
}
