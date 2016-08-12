package com.fx.giftsUser.model;

import java.util.Date;

import com.fx.giftsActivity.model.GiftsActivity;
import mybatis.framework.core.model.BaseValueObject;

public class GiftsUser extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer gid;

    private Integer isDel;

    private Integer status;

    private Date participateTime;

    private Date updateTime;

    private Date operateTime;

    private String operateUser;

    private String remark;
    
    private Integer depositId;
    
    private Date endTime;
    
    private Integer mt4Account;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    GiftsActivity giftsActivity;

    public GiftsActivity getGiftsActivity() {
        return giftsActivity;
    }

    public void setGiftsActivity(GiftsActivity giftsActivity) {
        this.giftsActivity = giftsActivity;
    }

    public Integer getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(Integer mt4Account) {
		this.mt4Account = mt4Account;
	}

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(Date participateTime) {
        this.participateTime = participateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser == null ? null : operateUser.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}