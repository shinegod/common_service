package com.fx.user.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class TradeQuery extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private String documentnumber;

    private BigDecimal disputeamount;

    private String title;

    private Integer status;
    
    private String content;
    
    private int mt4Account;
    private int isDel=0;

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

    public String getDocumentnumber() {
        return documentnumber;
    }

    public void setDocumentnumber(String documentnumber) {
        this.documentnumber = documentnumber == null ? null : documentnumber.trim();
    }

    public BigDecimal getDisputeamount() {
        return disputeamount;
    }

    public void setDisputeamount(BigDecimal disputeamount) {
        this.disputeamount = disputeamount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public int getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(int mt4Account) {
		this.mt4Account = mt4Account;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
    
}