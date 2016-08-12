package com.fx.global.model;

import mybatis.framework.core.model.BaseValueObject;

public class Group extends BaseValueObject{
	private Integer id;
	private String groupName;
	private String groupNameMT4;
	private Integer companyId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupNameMT4() {
		return groupNameMT4;
	}
	public void setGroupNameMT4(String groupNameMT4) {
		this.groupNameMT4 = groupNameMT4;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	


}
