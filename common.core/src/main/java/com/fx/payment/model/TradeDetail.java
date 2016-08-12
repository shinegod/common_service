package com.fx.payment.model;

import com.fx.user.model.UserRegister;
import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;

public class TradeDetail extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer payerId;
    
    private String bankName;

    private int payerType;

    private int operType;

    private BigDecimal operMoney;
    
    private BigDecimal actualMoney;
    private String currency;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;
    
    private int isDel = 0;
    
    private String branchName;
    
    private String swiftCode;
    
    private String bakName;
    
    private String cardNumber;
    
    private int mt4Account;
    
    private BigDecimal rate;

    private BigDecimal actualFee;

    private BigDecimal realMoney;
    
    private String refuseReason;

    private String remark;

    private BigDecimal fee;

    //所有汇款回单附件的id
    private String edocIds;

    private BigDecimal depositMoney;

    private Integer usermt4accountId;

    private String orderNumber;

    private String merOrderNum;

    private String comment;

    UserRegister userRegister;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMerOrderNum() {
        return merOrderNum;
    }

    public void setMerOrderNum(String merOrderNum) {
        this.merOrderNum = merOrderNum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getUsermt4accountId() {
        return usermt4accountId;
    }

    public void setUsermt4accountId(Integer usermt4accountId) {
        this.usermt4accountId = usermt4accountId;
    }

    public BigDecimal getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPayerId() {
        return payerId;
    }

    public void setPayerId(Integer payerId) {
        this.payerId = payerId;
    }
    public BigDecimal getActualMoney() {
		return actualMoney;
	}
	public void setActualMoney(BigDecimal actualMoney) {
		this.actualMoney = actualMoney;
    }

    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getPayerType() {
        return payerType;
    }

    public void setPayerType(int payerType) {
        this.payerType = payerType;
    }

    public int getOperType() {
        return operType;
    }

    public void setOperType(int operType) {
        this.operType = operType;
    }

    public BigDecimal getOperMoney() {
        return operMoney;
    }

    public void setOperMoney(BigDecimal operMoney) {
        this.operMoney = operMoney;
    }

    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBakName() {
		return bakName;
	}

	public void setBakName(String bakName) {
		this.bakName = bakName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(int mt4Account) {
		this.mt4Account = mt4Account;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }

    public BigDecimal getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(BigDecimal realMoney) {
        this.realMoney = realMoney;
    }

    public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEdocIds() {
        return edocIds;
    }

    public void setEdocIds(String edocIds) {
        this.edocIds = edocIds;
    }
}