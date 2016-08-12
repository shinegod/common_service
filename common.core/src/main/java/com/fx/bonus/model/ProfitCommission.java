package com.fx.bonus.model;

import java.math.BigDecimal;
import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class ProfitCommission extends BaseValueObject {
    private String guid;

    private String userId;

    private Integer mt4account;

    private String ibId;

    private String ibIdType;

    private String paymentDate;

    private BigDecimal profitAmount;

    private Integer agentaccount;

    private Date createdate;

    private Date updatedate;

    private Integer status;
    
    private BigDecimal unitPrice;
    
    private int dataSourceId;
    
    private int orderId;
    
    private BigDecimal totalProfit;
    
    private BigDecimal totalPl;
    
    private String agentName;
    
    private String userEnName;
    
    private BigDecimal porgation;
    
    private String specialId;

    private Date closeTime; //关盘时间

    private String ticket;  //交易编号

    private String comment; //描述

    private BigDecimal swaps; //隔夜利息

    private String symbol;  //交易货币对

    private BigDecimal profit;  //返佣

    private String mt4AccountGroup; //账号组

    private Integer rules; //计算的规则

    private BigDecimal rate; //汇率

    private BigDecimal parRate; //向上级返金账号的汇率

    public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getUserEnName() {
		return userEnName;
	}

	public void setUserEnName(String userEnName) {
		this.userEnName = userEnName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public BigDecimal getTotalPl() {
		return totalPl;
	}

	public void setTotalPl(BigDecimal totalPl) {
		this.totalPl = totalPl;
	}

	public int getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(int dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate == null ? null : paymentDate.trim();
    }

    public BigDecimal getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(BigDecimal profitAmount) {
        this.profitAmount = profitAmount;
    }

    public Integer getAgentaccount() {
        return agentaccount;
    }

    public void setAgentaccount(Integer agentaccount) {
        this.agentaccount = agentaccount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public BigDecimal getPorgation() {
		return porgation;
	}

	public void setPorgation(BigDecimal porgation) {
		this.porgation = porgation;
	}

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getSwaps() {
        return swaps;
    }

    public void setSwaps(BigDecimal swaps) {
        this.swaps = swaps;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getMt4AccountGroup() {
        return mt4AccountGroup;
    }

    public void setMt4AccountGroup(String mt4AccountGroup) {
        this.mt4AccountGroup = mt4AccountGroup;
    }

    public Integer getRules() {
        return rules;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getParRate() {
        return parRate;
    }

    public void setParRate(BigDecimal parRate) {
        this.parRate = parRate;
    }
}