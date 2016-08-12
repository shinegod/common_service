package com.fx.bonus.model;

import java.math.BigDecimal;
import java.util.Date;

import mybatis.framework.core.model.BaseValueObject;

public class CommissionSpecialRules extends BaseValueObject {
    private Integer id;

    private Integer specialId;

    private Integer uid;

    private BigDecimal proportion;

    private Integer tradinggroupid;

    private Integer agentAccount;

    private String updateUser;

    private Date updateDate;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public Integer getTradinggroupid() {
        return tradinggroupid;
    }

    public void setTradinggroupid(Integer tradinggroupid) {
        this.tradinggroupid = tradinggroupid;
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
}