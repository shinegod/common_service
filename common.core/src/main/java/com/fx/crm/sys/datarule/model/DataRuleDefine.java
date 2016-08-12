package com.fx.crm.sys.datarule.model;

import mybatis.framework.core.model.BaseValueObject;

public class DataRuleDefine extends BaseValueObject {
    private String id;

    private String menuId;

    private String sqlId;

    private String sqlExp;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId == null ? null : sqlId.trim();
    }

    public String getSqlExp() {
        return sqlExp;
    }

    public void setSqlExp(String sqlExp) {
        this.sqlExp = sqlExp == null ? null : sqlExp.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}