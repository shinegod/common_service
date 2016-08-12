package com.fx.bonus.model;

import java.math.BigDecimal;
import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class TradeDictionary extends BaseValueObject {
    private Integer id;

    private String tradeType;

    private BigDecimal contractSize;

    private Date createDate;

    private Date updateTime;

    private Integer status = ((0));

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public BigDecimal getContractSize() {
        return contractSize;
    }

    public void setContractSize(BigDecimal contractSize) {
        this.contractSize = contractSize;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}