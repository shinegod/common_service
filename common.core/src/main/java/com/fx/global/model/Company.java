package com.fx.global.model;

import mybatis.framework.core.model.BaseValueObject;

public class Company extends BaseValueObject{
	private int id;
	private String companyName;
	private int parentCompanyId;
	private String emailHost;
	private String emailUserName;
	private String emailPassword;
	private String serverName;
	private String softwareName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String host) {
		this.emailHost = host;
	}
	public String getEmailUserName() {
		return emailUserName;
	}
	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public int getParentCompanyId() {
		return parentCompanyId;
	}
	public void setParentCompanyId(int parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}
	
	
}
