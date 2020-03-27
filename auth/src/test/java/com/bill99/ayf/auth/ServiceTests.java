package com.bill99.ayf.auth;

import com.alibaba.fastjson.JSON;
import com.bill99.ayf.auth.common.SysCode;
import com.bill99.ayf.auth.dto.OrgDTO;
import com.bill99.ayf.auth.dto.PermissionDTO;
import com.bill99.ayf.auth.dto.PermissionTree;
import com.bill99.ayf.auth.dto.RoleDTO;
import com.bill99.ayf.auth.dto.UserDTO;
import com.bill99.ayf.auth.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * andy.an
 * 2020/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @Autowired
    private AuthService authService;

    @Test
    public void testUser() {
        UserDTO dto = new UserDTO();
        dto.setName("nnnn");
        dto.setRoleIds(Arrays.asList(200L, 101L, 302L, 105L));
        authService.addUser(dto);
    }

    @Test
    public void testRole() {
        UserDTO dto = new UserDTO();
        dto.setId(10L);
        dto.setRoleIds(Arrays.asList(200L, 101L, 302L, 105L));
//        authService.addUserRoles(dto);

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(222L);
        roleDTO.setPermissionIds(Arrays.asList(522L, 212L, 121L));
//        authService.addRolePermissions(roleDTO);

        roleDTO.setId(1L);
        authService.deleteRole(roleDTO);
    }
    

    @Test
    public void testOrg() {
        OrgDTO dto = new OrgDTO();
        dto.setCode("oooo1");
        dto.setName("摩萨德");
        authService.addOrg(dto);

        dto.setId(1L);
        dto.setSuperviseId(1000L);
        authService.updateOrg(dto);
    }

    @Test
    public void testPermission() {
//        List<PermissionTree> treeList = authService.findPermissions(SysCode.SYS.code(), 1L);
//        System.out.println(JSON.toJSONString(treeList));
//
        List<PermissionTree> treeCheckedList = authService.findCheckedPermissions(SysCode.SYS.code(), 10L);
        System.out.println(JSON.toJSONString(treeCheckedList));

//        PermissionDTO dto = new PermissionDTO();
//        dto.setId(10L);
//        authService.deletePermission(dto);
    }
    
    @Test
    public void testUserList() {
        System.out.println(JSON.toJSONString(authService.listUser()));
    }
}
