package com.fx.bbookuserReport.model;

import mybatis.framework.core.model.BaseValueObject;

public class BbookuserReport extends BaseValueObject {
    private Integer id;

    private Integer login;

    private Long oldEquity;

    private Long deposit;

    private Long withdraw;

    private double trades;

    private double exchange;

    private double gold;

    private double silver;

    private double oil;

    private Long newEquity;

    private String createDate;

    private double a50;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public Long getOldEquity() {
        return oldEquity;
    }

    public void setOldEquity(Long oldEquity) {
        this.oldEquity = oldEquity;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }

    public double getTrades() {
        return trades;
    }

    public void setTrades(double trades) {
        this.trades = trades;
    }

    public double getExchange() {
        return exchange;
    }

    public void setExchange(double exchange) {
        this.exchange = exchange;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public double getSilver() {
        return silver;
    }

    public void setSilver(double silver) {
        this.silver = silver;
    }

    public double getOil() {
        return oil;
    }

    public void setOil(double oil) {
        this.oil = oil;
    }

    public Long getNewEquity() {
        return newEquity;
    }

    public void setNewEquity(Long newEquity) {
        this.newEquity = newEquity;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getA50() {
        return a50;
    }

    public void setA50(double a50) {
        this.a50 = a50;
    }
}