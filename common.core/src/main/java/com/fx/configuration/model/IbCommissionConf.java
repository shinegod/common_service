package com.fx.configuration.model;

import java.math.BigDecimal;
import java.util.Date;

import mybatis.framework.core.model.BaseValueObject;

public class IbCommissionConf extends BaseValueObject {
    private Integer id;

    private String name;

    private Integer mt4GroupId;

    private BigDecimal ibCommission;

    private int isDel;

    private Date createTime;

    private Date updateTime;

    private Integer createUid;

    private Integer updateUid;

    private String createIp;

    private String updateIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMt4GroupId() {
        return mt4GroupId;
    }

    public void setMt4GroupId(Integer mt4GroupId) {
        this.mt4GroupId = mt4GroupId;
    }

    public BigDecimal getIbCommission() {
        return ibCommission;
    }

    public void setIbCommission(BigDecimal ibCommission) {
        this.ibCommission = ibCommission;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }


    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }
}