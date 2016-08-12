package com.fx.mt4TradeRecord.model;

import com.fx.payment.model.UserMT4Account;
import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class DepositWithdrawalAdjReport extends BaseValueObject {

    private String ibName;

    private String name;

    private Integer account;

    private Integer userStatus;

    private Double deposit;

    private Double withdrawal;

    private Double adjustment;

    private String operationTime;

    private String comment;

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getIbName() {
        return ibName;
    }

    public void setIbName(String ibName) {
        this.ibName = ibName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Double getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Double adjustment) {
        this.adjustment = adjustment;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}