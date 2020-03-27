package com.bill99.ayf.auth.dto;

import com.alibaba.fastjson.annotation.JSONType;

import java.util.List;

/**
 * andy.an
 * 2020/3/26
 */
@JSONType(orders = {"id", "pid", "name", "path", "permission", "checked", "children"})
public class PermissionTree {
    private Long id;
    private Long pid;
    private String name;
    private String path;
    private String permission;
    private boolean checked;
    private List<PermissionTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<PermissionTree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionTree> children) {
        this.children = children;
    }
}
