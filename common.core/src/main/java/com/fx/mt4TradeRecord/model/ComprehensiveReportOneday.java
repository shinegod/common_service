package com.fx.mt4TradeRecord.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class ComprehensiveReportOneday extends BaseValueObject {
    private Integer id;

    private String ibName;

    private Integer account;

    private String name;

    private BigDecimal earlyAmount;

    private BigDecimal amountIn;

    private BigDecimal amountOut;

    private BigDecimal commission;

    private BigDecimal taxes;

    private BigDecimal profit;

    private BigDecimal lastAmount;

    private String createDate;

    private String updateDate;

    private String calculateDate;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIbName() {
        return ibName;
    }

    public void setIbName(String ibName) {
        this.ibName = ibName == null ? null : ibName.trim();
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getEarlyAmount() {
        return earlyAmount;
    }

    public void setEarlyAmount(BigDecimal earlyAmount) {
        this.earlyAmount = earlyAmount;
    }

    public BigDecimal getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }

    public BigDecimal getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(BigDecimal lastAmount) {
        this.lastAmount = lastAmount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getCalculateDate() {
        return calculateDate;
    }

    public void setCalculateDate(String calculateDate) {
        this.calculateDate = calculateDate == null ? null : calculateDate.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}