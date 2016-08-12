package com.fx.global.model;

import mybatis.framework.core.model.BaseValueObject;

public class UidGenerator extends BaseValueObject{
	private Integer id;
	
	private Integer nextIdLive;
	
	private Integer nextIdDemo;
	
	private Integer companyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNextIdLive() {
		return nextIdLive;
	}

	public void setNextIdLive(Integer nextIdLive) {
		this.nextIdLive = nextIdLive;
	}

	public Integer getNextIdDemo() {
		return nextIdDemo;
	}

	public void setNextIdDemo(Integer nextIdDemo) {
		this.nextIdDemo = nextIdDemo;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
