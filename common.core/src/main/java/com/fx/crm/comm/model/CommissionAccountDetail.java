package com.fx.crm.comm.model;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

public class CommissionAccountDetail extends BaseValueObject {
    private String guid;

    private String baseId;

    private String userId;

    private String ibId;

    private String ibIdType;

    private BigDecimal commission;

    private Date settledate;

    private String comment;

    private BigDecimal volume;

    private String source = "((0))";

    private String status;

    private Integer mt4account;

    private String symbol;

    private Date createdate;

    private Date updatedate;
    
    private Integer dataSourceId;
    
    private Integer mt4DataSourceType;
    
    private BigDecimal currencyCommission;

    public Integer getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public Integer getMt4DataSourceType() {
		return mt4DataSourceType;
	}

	public void setMt4DataSourceType(Integer mt4DataSourceType) {
		this.mt4DataSourceType = mt4DataSourceType;
	}

	public BigDecimal getCurrencyCommission() {
		return currencyCommission;
	}

	public void setCurrencyCommission(BigDecimal currencyCommission) {
		this.currencyCommission = currencyCommission;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId == null ? null : baseId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIbId() {
        return ibId;
    }

    public void setIbId(String ibId) {
        this.ibId = ibId == null ? null : ibId.trim();
    }

    public String getIbIdType() {
        return ibIdType;
    }

    public void setIbIdType(String ibIdType) {
        this.ibIdType = ibIdType == null ? null : ibIdType.trim();
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Date getSettledate() {
        return settledate;
    }

    public void setSettledate(Date settledate) {
        this.settledate = settledate;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}