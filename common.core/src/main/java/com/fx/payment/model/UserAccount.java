package com.fx.payment.model;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;

public class UserAccount extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private String tradeCode;
    
    private int mt4Account;
    
    private int mt4AccountType;

    private String mt4GroupId;
    
    private BigDecimal accountBalance;
    
    private BigDecimal freezeBalance;

    private String operatePassword = "";

    private BigDecimal allFundsInto;

    private BigDecimal allFundsOut;

    private Integer fundsIntoNumber;

    private Integer fundsOutNumber;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel;
    
    private int ib_id;

    public int getIb_id() {
		return ib_id;
	}

	public void setIb_id(int ib_id) {
		this.ib_id = ib_id;
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

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }
    
    public void setMt4Account(int mt4Account){
    	this.mt4Account = mt4Account;
    }
    
    public int getMt4Account(){
    	return this.mt4Account;
    }
    
    public void setMt4AccountType(int mt4AccountType){
    	this.mt4AccountType = mt4AccountType;
    }
    
    public int getMt4AccountType(){
    	return this.mt4AccountType;
    }

    public String getMt4GroupId() {
		return mt4GroupId;
	}

	public void setMt4GroupId(String mt4GroupId) {
		this.mt4GroupId = mt4GroupId;
	}

	public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public BigDecimal getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(BigDecimal freezeBalance) {
		this.freezeBalance = freezeBalance;
	}

	public String getOperatePassword() {
        return operatePassword;
    }

    public void setOperatePassword(String operatePassword) {
        this.operatePassword = operatePassword == null ? null : operatePassword.trim();
    }

    public BigDecimal getAllFundsInto() {
        return allFundsInto;
    }

    public void setAllFundsInto(BigDecimal allFundsInto) {
        this.allFundsInto = allFundsInto;
    }

    public BigDecimal getAllFundsOut() {
        return allFundsOut;
    }

    public void setAllFundsOut(BigDecimal allFundsOut) {
        this.allFundsOut = allFundsOut;
    }

    public Integer getFundsIntoNumber() {
        return fundsIntoNumber;
    }

    public void setFundsIntoNumber(Integer fundsIntoNumber) {
        this.fundsIntoNumber = fundsIntoNumber;
    }

    public Integer getFundsOutNumber() {
        return fundsOutNumber;
    }

    public void setFundsOutNumber(Integer fundsOutNumber) {
        this.fundsOutNumber = fundsOutNumber;
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
        this.createTime = createTime == null ? null : createTime.trim();
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
        this.updateTime = updateTime == null ? null : updateTime.trim();
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
}