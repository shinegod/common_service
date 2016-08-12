package com.fx.bonus.model;

import java.math.BigDecimal;

import mybatis.framework.core.model.BaseValueObject;

public class Bonus extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private BigDecimal amount;

    private String paymentDate;

    private Integer fromTransactionId;

    private String comment;

    private int isPaid;

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

    public BigDecimal getAmount() {
        return amount;
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
}