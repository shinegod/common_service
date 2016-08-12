package com.fx.user.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class MT4TransferLog extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer mt4transferid;

    private BigDecimal depositamount;

    private BigDecimal withdrawamount;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel = ((0));

    private String currency;

    private Integer frommt4account;

    private Integer tomt4account;
    
    private String log;

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

    public Integer getMt4transferid() {
        return mt4transferid;
    }

    public void setMt4transferid(Integer mt4transferid) {
        this.mt4transferid = mt4transferid;
    }

    public BigDecimal getDepositamount() {
        return depositamount;
    }

    public void setDepositamount(BigDecimal depositamount) {
        this.depositamount = depositamount;
    }

    public BigDecimal getWithdrawamount() {
        return withdrawamount;
    }

    public void setWithdrawamount(BigDecimal withdrawamount) {
        this.withdrawamount = withdrawamount;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getFrommt4account() {
        return frommt4account;
    }

    public void setFrommt4account(Integer frommt4account) {
        this.frommt4account = frommt4account;
    }

    public Integer getTomt4account() {
        return tomt4account;
    }

    public void setTomt4account(Integer tomt4account) {
        this.tomt4account = tomt4account;
    }

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
    
}