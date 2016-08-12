package com.fx.task.model;

import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class TaskManage extends BaseValueObject {
    private Integer userId;

    private String tid;
    
    private Integer mt4Account;

    private String applyType;

    private Date applyTime;

    private Integer status;

    private String reason;

    private String refusereason;

    private String dutyOfficer;
    
    private String comment;
    //回显申请人姓名
    private String userName;

    // MT4组
    private String mt4GroupId;

    public String getMt4GroupId() {
        return mt4GroupId;
    }

    public void setMt4GroupId(String mt4GroupId) {
        this.mt4GroupId = mt4GroupId;
    }

    public String getTid() {
		return tid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMt4Account() {
        return mt4Account;
    }

    public void setMt4Account(Integer mt4Account) {
        this.mt4Account = mt4Account;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRefusereason() {
        return refusereason;
    }

    public void setRefusereason(String refusereason) {
        this.refusereason = refusereason == null ? null : refusereason.trim();
    }

    public String getDutyOfficer() {
        return dutyOfficer;
    }

    public void setDutyOfficer(String dutyOfficer) {
        this.dutyOfficer = dutyOfficer == null ? null : dutyOfficer.trim();
    }
}