package com.fx.bonus.model;

import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class CommissionSpecial extends BaseValueObject {
    private Integer id;

    private String ruleId;

    private Integer uid;

    private Integer specialUid;
    
    private Integer uidAccount;

    private Integer customerId;

    private Integer agentAccount;

    private String updateUser;

    private Date updateDate;
    
    private int rangeType=1; //1表示所有账号，2表示自己的账号
    
    private int commissionType=1; //1表示手数，2表示附加

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRangeType() {
		return rangeType;
	}

	public void setRangeType(int rangeType) {
		this.rangeType = rangeType;
	}

	public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public Integer getUidAccount() {
		return uidAccount;
	}

	public void setUidAccount(Integer uidAccount) {
		this.uidAccount = uidAccount;
	}

	public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(Integer agentAccount) {
        this.agentAccount = agentAccount;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getSpecialUid() {
		return specialUid;
	}

	public void setSpecialUid(Integer specialUid) {
		this.specialUid = specialUid;
	}

}