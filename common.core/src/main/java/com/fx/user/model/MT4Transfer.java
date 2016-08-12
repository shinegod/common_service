package com.fx.user.model;

import java.math.BigDecimal;

import mybatis.framework.core.model.BaseValueObject;

public class MT4Transfer extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private BigDecimal amount;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel = ((0));

    private String currency;
    
    private String from_currency;
    
    private String to_currency;

    private Integer frommt4account;

    private Integer tomt4account;
    
    private String refuseReason;
    
    private BigDecimal audit_rate;

    private BigDecimal apply_rate;
    
    private String reason;
    
    private String comment;
    
    private String auditName;
    
    private BigDecimal audit_amount;
    
  //用来存储数据源id
  	private int dataSourceId;
    
    //用于回显
    private String userEmail;
   //用于回显
    private String userName;
    
    public int getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(int dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public BigDecimal getAudit_amount() {
		return audit_amount;
	}

	public void setAudit_amount(BigDecimal audit_amount) {
		this.audit_amount = audit_amount;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFrom_currency() {
		return from_currency;
	}

	public void setFrom_currency(String from_currency) {
		this.from_currency = from_currency;
	}

	public String getTo_currency() {
		return to_currency;
	}

	public void setTo_currency(String to_currency) {
		this.to_currency = to_currency;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getAudit_rate() {
		return audit_rate;
	}

	public void setAudit_rate(BigDecimal audit_rate) {
		this.audit_rate = audit_rate;
	}

	public BigDecimal getApply_rate() {
		return apply_rate;
	}

	public void setApply_rate(BigDecimal apply_rate) {
		this.apply_rate = apply_rate;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getFrommt4account() {
        return frommt4account;
    }

    public void setFrommt4account(Integer frommt4account) {
        this.frommt4account = frommt4account;
    }

    public Integer getTomt4account() {
        return tomt4account;
    }

    public void setTomt4account(Integer tomt4account) {
        this.tomt4account = tomt4account;
    }
}