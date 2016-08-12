package com.fx.admin.model;

import mybatis.framework.core.model.BaseValueObject;

public class AdminLog extends BaseValueObject {
    private Integer id;

    private Integer adminId;

    private Short operType;

    private String operContext;

    private String operTime;

    private String operIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Short getOperType() {
        return operType;
    }

    public void setOperType(Short operType) {
        this.operType = operType;
    }

    public String getOperContext() {
        return operContext;
    }

    public void setOperContext(String operContext) {
        this.operContext = operContext == null ? null : operContext.trim();
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime == null ? null : operTime.trim();
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp == null ? null : operIp.trim();
    }
}