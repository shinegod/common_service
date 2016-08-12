package com.fx.mail.model;

import mybatis.framework.core.model.BaseValueObject;

public class MailServer extends BaseValueObject {
    private Integer id;

    private String mailServerName;

    private Integer mailServerType;
    
    private String apiKey;

    private String textUrl;

    private String templateUrl;

    private String host;

    private Integer port;

    private String protocol;

    private Integer isDel;

    public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMailServerName() {
        return mailServerName;
    }

    public void setMailServerName(String mailServerName) {
        this.mailServerName = mailServerName == null ? null : mailServerName.trim();
    }

    public Integer getMailServerType() {
        return mailServerType;
    }

    public void setMailServerType(Integer mailServerType) {
        this.mailServerType = mailServerType;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl == null ? null : textUrl.trim();
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl == null ? null : templateUrl.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}