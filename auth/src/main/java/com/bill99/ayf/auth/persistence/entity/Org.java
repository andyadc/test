package com.bill99.ayf.auth.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class Org implements Serializable {
    private Long id;

    private String name;

    private String code;

    private Long superviseId;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getSuperviseId() {
        return superviseId;
    }

    public void setSuperviseId(Long superviseId) {
        this.superviseId = superviseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}