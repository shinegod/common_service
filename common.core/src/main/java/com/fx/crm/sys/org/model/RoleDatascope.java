package com.fx.crm.sys.org.model;

import mybatis.framework.core.model.BaseValueObject;

public class RoleDatascope extends BaseValueObject {
    private Integer roleId;

    private Integer orgId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}