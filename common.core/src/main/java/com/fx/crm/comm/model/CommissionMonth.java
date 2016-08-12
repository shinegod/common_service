package com.fx.crm.comm.model;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.payment.model.UserMT4Account;
import com.fx.user.model.UserRegister;

public class CommissionMonth extends BaseValueObject {
    private String guid;

    private String userId;

    private Integer mt4account;

    private String ibId;

    private String ibIdType;

    private String paymentDate;

    private BigDecimal volume;

    private BigDecimal withdrawAmount;

    private BigDecimal depositAmount;

    private Date createdate;

    private Date updatedate;
    
    private int status;

    private UserRegister userRegister;
    
    private UserMT4Account userMT4Account;
    
    private Mt4Users mt4Users;

    private String roleName;

    private String agentName;

    private String createdateView;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCreatedateView() {
        return createdateView;
    }

    public void setCreatedateView(String createdateView) {
        this.createdateView = createdateView;
    }

    public Mt4Users getMt4Users() {
		return mt4Users;
	}

	public void setMt4Users(Mt4Users mt4Users) {
		this.mt4Users = mt4Users;
	}

	public UserMT4Account getUserMT4Account() {
		return userMT4Account;
	}

    
    private String email;
    private String enName;
    
    private String statusValue;



	public void setUserMT4Account(UserMT4Account userMT4Account) {
		this.userMT4Account = userMT4Account;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public int getStatus() {

		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public String getIbId() {
        return ibId;
    }

    public void setIbId(String ibId) {
        this.ibId = ibId == null ? null : ibId.trim();
    }

    public String getIbIdType() {
        return ibIdType;
    }

    public void setIbIdType(String ibIdType) {
        this.ibIdType = ibIdType == null ? null : ibIdType.trim();
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate == null ? null : paymentDate.trim();
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
    public String getEnName() {
        return enName;
    }
    public void setEnName(String enName) {
        this.enName = enName;
    }
}