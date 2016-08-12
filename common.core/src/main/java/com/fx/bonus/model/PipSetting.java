package com.fx.bonus.model;

import java.util.Date;

import mybatis.framework.core.model.BaseValueObject;

public class PipSetting extends BaseValueObject {
    private Integer id;

    private String ruleName;

    private String ruleExpression;

    private int status;

    private String updateUser;
    
    private String tradeVeriaty;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleExpression() {
        return ruleExpression;
    }

    public void setRuleExpression(String ruleExpression) {
        this.ruleExpression = ruleExpression == null ? null : ruleExpression.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getTradeVeriaty() {
		return tradeVeriaty;
	}

	public void setTradeVeriaty(String tradeVeriaty) {
		this.tradeVeriaty = tradeVeriaty;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}