package com.fx.bonus.model;

import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class CommissionFeeLog extends BaseValueObject {
    private Integer id;

    private Integer pid;

    private Integer uid;

    private Integer createUserId;

    private String comment;

    private Integer timesMonth;

    private Integer mt4Account;

    private Date createTime;

    //页面回显
    private String accountName;

    private String agentName;

    private IbcommissionPaymentLog ibcommissionPaymentLog;

    public IbcommissionPaymentLog getIbcommissionPaymentLog() {
        return ibcommissionPaymentLog;
    }

    public void setIbcommissionPaymentLog(IbcommissionPaymentLog ibcommissionPaymentLog) {
        this.ibcommissionPaymentLog = ibcommissionPaymentLog;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getTimesMonth() {
        return timesMonth;
    }

    public void setTimesMonth(Integer timesMonth) {
        this.timesMonth = timesMonth;
    }

    public Integer getMt4Account() {
        return mt4Account;
    }

    public void setMt4Account(Integer mt4Account) {
        this.mt4Account = mt4Account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}