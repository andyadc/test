package com.bill99.ayf.auth.dto;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * andy.an
 * 2020/3/25
 */
public class UserDTO {

    private Long id;
    private String name;
    private Long orgId;
    private List<Long> roleIds;

    public static void main(String[] args) {
        UserDTO dto = new UserDTO();
        List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L);
        dto.setOrgId(1L);
        dto.setRoleIds(ids);
        dto.setId(1L);
        dto.setName("撒大大的");
        System.out.println(JSON.toJSONString(dto));
        
        String json = "{\"id\":1,\"name\":\"撒大大的\",\"orgId\":1,\"roleIds\":[1,2,3,4]}";
        dto = JSON.parseObject(json, UserDTO.class);
        System.out.println(dto.getRoleIds());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
