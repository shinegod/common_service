package com.fx.mt4TradeRecord.model;

import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class StopLossTakeProfitReport extends BaseValueObject {

    private String ibName;

    private String name;

    private int account;

    private int tacket;

    private String type;

    private String symbol;

    private double volume;

    private double openPrice;

    private String openTime;

    private double sl;

    private double tp;

    private double commission;

    private double swaps;

    private  double profit;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
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

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getTacket() {
        return tacket;
    }

    public void setTacket(int tacket) {
        this.tacket = tacket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getSwaps() {
        return swaps;
    }

    public void setSwaps(double swaps) {
        this.swaps = swaps;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}