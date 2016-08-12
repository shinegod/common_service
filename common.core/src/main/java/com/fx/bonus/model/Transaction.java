package com.fx.bonus.model;

import java.math.BigDecimal;

import mybatis.framework.core.model.BaseValueObject;

public class Transaction extends BaseValueObject {
    private Integer id;    

    private Integer uid;                //用户编号 必填

    private Integer mt4Account;			//mt4帐户  必填

    private int transactionType;        //入金:0 出金:1 必填

    private String transactionDate;     //最好是到账的那天   必填

    private BigDecimal transactionAmount;		// 金额 入金为正 出金为负 必填

    private String settleDate;			// 不填
    
    private String isIbSettledDate;
    
    private String isCommonSettledDate;

    private BigDecimal settleAmount;			// 同金额          必填
    
    private BigDecimal isIBSettleAmount;
    
    private BigDecimal isCommonSettledAmount;

    private Integer isSettled = (0);  	// 0                   必填

    private String comment;				// 不涉及计算 可放入 出入金方式 和执行人 如“via bank/card/atm, approved by admin ?” 选填

    private Integer tradeId;			// 对应出入金表格里的id 必填
    
    public boolean updated=false;		// 不填
    
    private Integer isIbSettled = (0);
    
    private Integer isCommonSettled = (0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMt4Account() {
        return mt4Account;
    }

    public BigDecimal getIsIBSettleAmount() {
		return isIBSettleAmount;
	}

	public String getIsIbSettledDate() {
		return isIbSettledDate;
	}

	public void setIsIbSettledDate(String isIbSettledDate) {
		this.isIbSettledDate = isIbSettledDate;
	}

	public String getIsCommonSettledDate() {
		return isCommonSettledDate;
	}

	public void setIsCommonSettledDate(String isCommonSettledDate) {
		this.isCommonSettledDate = isCommonSettledDate;
	}

	public void setIsIBSettleAmount(BigDecimal isIBSettleAmount) {
		this.isIBSettleAmount = isIBSettleAmount;
	}

	public BigDecimal getIsCommonSettledAmount() {
		return isCommonSettledAmount;
	}

	public void setIsCommonSettledAmount(BigDecimal isCommonSettledAmount) {
		this.isCommonSettledAmount = isCommonSettledAmount;
	}

	public void setMt4Account(int mt4Account) {
        this.mt4Account = mt4Account;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate == null ? null : transactionDate.trim();
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public int getIsSettled() {
        return isSettled;
    }

    public void setIsSettled(int isSettled) {
        this.isSettled = isSettled;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

	public Integer getIsIbSettled() {
		return isIbSettled;
	}

	public void setIsIbSettled(Integer isIbSettled) {
		this.isIbSettled = isIbSettled;
	}

	public void setIsSettled(Integer isSettled) {
		this.isSettled = isSettled;
	}

	public Integer getIsCommonSettled() {
		return isCommonSettled;
	}

	public void setIsCommonSettled(Integer isCommonSettled) {
		this.isCommonSettled = isCommonSettled;
	}
    
}