package com.fx.bonus.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class EaCommissionDetail extends BaseValueObject {
    private Integer id;

    private Integer eaid;

    private BigDecimal amount;

    private String paymentDate;

    private Integer fromTransactionId;

    private String comment;

    private int isPaid = ((0));

    private BigDecimal volume;
    
    private Integer eadeveloperid;

    private Integer source = ((0));

    private int status;

    private Integer mt4account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEaid() {
        return eaid;
    }

    public void setEaid(Integer eaid) {
        this.eaid = eaid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getEadeveloperid() {
		return eadeveloperid;
	}

	public void setEadeveloperid(Integer eadeveloperid) {
		this.eadeveloperid = eadeveloperid;
	}

	public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate == null ? null : paymentDate.trim();
    }

    public Integer getFromTransactionId() {
        return fromTransactionId;
    }

    public void setFromTransactionId(Integer fromTransactionId) {
        this.fromTransactionId = fromTransactionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }
}