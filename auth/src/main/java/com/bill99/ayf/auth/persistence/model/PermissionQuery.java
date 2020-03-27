package com.bill99.ayf.auth.persistence.model;

/**
 * andy.an
 * 2020/3/26
 */
public class PermissionQuery {

    private Long userId;
    private String sysCode;
    private String permission;

    public PermissionQuery(Long userId) {
        this.userId = userId;
    }

    public PermissionQuery(String sysCode) {
        this.sysCode = sysCode;
    }

    public PermissionQuery(Long userId, String sysCode) {
        this.userId = userId;
        this.sysCode = sysCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
