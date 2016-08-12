package com.fx.bonus.model;

import java.math.BigDecimal;

import mybatis.framework.core.model.BaseValueObject;

public class IBCommission extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer ibId;

    private BigDecimal amount;

    private String paymentDate;

    private Integer fromTransactionId;

    private String comment;
    
    private double volume = 0.0;
    
    private Integer status = ((0));

    private int isPaid = ((0));
    
    private int isInnerSales;

    public Integer getId() {
        return id;
    }

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

    public Integer getIbId() {
        return ibId;
    }

    public void setIbId(Integer ibId) {
        this.ibId = ibId;
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

    public Integer getFromTransactionId() {
        return fromTransactionId;
    }

    public void setFromTransactionId(Integer fromTransactionId) {
        this.fromTransactionId = fromTransactionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

	public int getIsInnerSales() {
		return isInnerSales;
	}

	public void setIsInnerSales(int isInnerSales) {
		this.isInnerSales = isInnerSales;
	}
    
}