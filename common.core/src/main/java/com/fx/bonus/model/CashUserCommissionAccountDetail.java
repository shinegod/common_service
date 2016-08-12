package com.fx.bonus.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class CashUserCommissionAccountDetail extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private BigDecimal amount;

    private String paymentDate;

    private String comment;

    private BigDecimal volume;

    private BigDecimal eausercommission;

    private Integer source = ((0));

    private int status;

    private Integer mt4account;

    private String settledate;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getEausercommission() {
        return eausercommission;
    }

    public void setEausercommission(BigDecimal eausercommission) {
        this.eausercommission = eausercommission;
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

    public String getSettledate() {
        return settledate;
    }

    public void setSettledate(String settledate) {
        this.settledate = settledate == null ? null : settledate.trim();
    }
}