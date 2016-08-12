package com.fx.crm.comm.model;

import mybatis.framework.core.model.BaseValueObject;

public class UserCommissionConf extends BaseValueObject {
    private String guid;

    private String userId;

    private String commRuleId;

    private String commType;

    private String commGroup;

    private String commAccount;
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommRuleId() {
        return commRuleId;
    }

    public void setCommRuleId(String commRuleId) {
        this.commRuleId = commRuleId == null ? null : commRuleId.trim();
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType == null ? null : commType.trim();
    }

    public String getCommGroup() {
        return commGroup;
    }

    public void setCommGroup(String commGroup) {
        this.commGroup = commGroup;
    }
    public String getCommAccount() {
        return commAccount;
    }
    public void setCommAccount(String commAccount) {
        this.commAccount = commAccount;
    }
}