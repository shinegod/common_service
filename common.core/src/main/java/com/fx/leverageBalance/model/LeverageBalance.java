package com.fx.leverageBalance.model;

import mybatis.framework.core.model.BaseValueObject;

public class LeverageBalance extends BaseValueObject {
    private Integer id;

    private Integer leverage;

    private Integer minBalance;

    private Integer maxBalance;

    private Integer status = ((0));

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public void setLeverage(Integer leverage) {
        this.leverage = leverage;
    }

    public Integer getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Integer minBalance) {
        this.minBalance = minBalance;
    }

    public Integer getMaxBalance() {
        return maxBalance;
    }

    public void setMaxBalance(Integer maxBalance) {
        this.maxBalance = maxBalance;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}