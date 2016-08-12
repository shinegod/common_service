package com.fx.bonus.model;

import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class  CommisionCoefficient extends BaseValueObject {
    private Integer id;

    private Integer ruleId;

    private Integer roleId;
    
    private Integer status=0;

    private String coefficient;

    private String updateUser;

    private Date updateDate;
    
    private int uid;
    
    private int agentAccount;

    public int getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(int agentAccount) {
		this.agentAccount = agentAccount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient == null ? null : coefficient.trim();
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

   
}