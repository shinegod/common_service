package com.fx.crm.sys.permission.model;

import mybatis.framework.core.model.BaseValueObject;

public class Permission extends BaseValueObject {
    private String id;

    private String permissionExp;

    private String menuid;

    private String permissionType;

    private String permissionDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPermissionExp() {
        return permissionExp;
    }

    public void setPermissionExp(String permissionExp) {
        this.permissionExp = permissionExp == null ? null : permissionExp.trim();
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType == null ? null : permissionType.trim();
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc == null ? null : permissionDesc.trim();
    }
}