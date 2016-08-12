package com.fx.mail.model;

import mybatis.framework.core.model.BaseValueObject;

public class MailUser extends BaseValueObject {
    private Integer id;

    private String apiUser;

    private String apiKey;

    private Integer isDel;

    private Integer apiUserType;

    private Integer sid;
    
    private Integer isDefault;

    public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiUser() {
        return apiUser;
    }

    public void setApiUser(String apiUser) {
        this.apiUser = apiUser == null ? null : apiUser.trim();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getApiUserType() {
        return apiUserType;
    }

    public void setApiUserType(Integer apiUserType) {
        this.apiUserType = apiUserType;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}