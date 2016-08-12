package com.fx.mail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mybatis.framework.core.model.BaseValueObject;

public class MailSendLog extends BaseValueObject {
    private Integer id;

    private String mailServerName;

    private Integer mailServerType;

    private String textUrl;

    private String templateUrl;

    private String host;

    private Integer port;

    private String protocol;

    private String apiUser;

    private String apiKey;

    private Integer apiUserType;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel = ((0));

    private String fromMail;

    private String fromName;

    private String vars;

    private String toMail;

    private String replyTo;

    private String bcc;

    private String cc;

    private String subject;

    private String templateInvokeName;

    private String html;

    private String useMaillist;

    private String links;

    private String addressbook;

    private String text;

    private Integer apiCode;

    private Integer httpCode;

    private Integer smtpCode;

    private Integer status;

    private Integer isResend;
    
    private Integer isTemplate;
    
    private String response;
    
    private Integer oldId;

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(Integer isTemplate) {
		this.isTemplate = isTemplate;
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

    public Integer getApiUserType() {
        return apiUserType;
    }

    public void setApiUserType(Integer apiUserType) {
        this.apiUserType = apiUserType;
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

    public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    @JsonIgnore
    public String getVars() {
        return vars;
    }

    public void setVars(String vars) {
        this.vars = vars == null ? null : vars.trim();
    }

    public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo == null ? null : replyTo.trim();
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc == null ? null : bcc.trim();
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getTemplateInvokeName() {
        return templateInvokeName;
    }

    public void setTemplateInvokeName(String templateInvokeName) {
        this.templateInvokeName = templateInvokeName == null ? null : templateInvokeName.trim();
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }

    public String getUseMaillist() {
        return useMaillist;
    }

    public void setUseMaillist(String useMaillist) {
        this.useMaillist = useMaillist == null ? null : useMaillist.trim();
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links == null ? null : links.trim();
    }

    public String getAddressbook() {
        return addressbook;
    }

    public void setAddressbook(String addressbook) {
        this.addressbook = addressbook == null ? null : addressbook.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getApiCode() {
        return apiCode;
    }

    public void setApiCode(Integer apiCode) {
        this.apiCode = apiCode;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Integer getSmtpCode() {
        return smtpCode;
    }

    public void setSmtpCode(Integer smtpCode) {
        this.smtpCode = smtpCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsResend() {
        return isResend;
    }

    public void setIsResend(Integer isResend) {
        this.isResend = isResend;
    }
}