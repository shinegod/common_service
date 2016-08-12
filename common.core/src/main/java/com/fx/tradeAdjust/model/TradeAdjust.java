package com.fx.tradeAdjust.model;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;

public class TradeAdjust extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer tid;

    private Integer mt4account;

    private Integer type;

    private String remark;

    private Integer targetUid;

    private Integer targetTid;

    private Integer targetMt4account;

    private Integer giftId;

    private BigDecimal operMoney;

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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(Integer targetUid) {
        this.targetUid = targetUid;
    }

    public Integer getTargetTid() {
        return targetTid;
    }

    public void setTargetTid(Integer targetTid) {
        this.targetTid = targetTid;
    }

    public Integer getTargetMt4account() {
        return targetMt4account;
    }

    public void setTargetMt4account(Integer targetMt4account) {
        this.targetMt4account = targetMt4account;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public BigDecimal getOperMoney() {
        return operMoney;
    }

    public void setOperMoney(BigDecimal operMoney) {
        this.operMoney = operMoney;
    }
}