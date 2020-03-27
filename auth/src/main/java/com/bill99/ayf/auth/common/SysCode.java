package com.bill99.ayf.auth.common;

/**
 * andy.an
 * 2020/3/26
 */
public enum SysCode {

    SYS("sys", "后台管理系统"),
    WEB("web", "网站前台"),
    ;

    String code;
    String desc;

    SysCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return code;
    }}
