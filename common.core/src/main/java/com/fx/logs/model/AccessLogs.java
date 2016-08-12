package com.fx.logs.model;

import mybatis.framework.core.model.BaseValueObject;

public class AccessLogs extends BaseValueObject {
   
	private static final long serialVersionUID = 6551168854277123079L;

	private Integer id;

    private String title;

    private String webId;

    private String userName;

    private String domain;

    private String accessIp;

    private String accessReferer;

    private String accessOperation;

    private String trackerId;

    private String accessTime;

    private String accessBrowser;

    private String userCookie;
    
    public String getAccessBrowser() {
		return accessBrowser;
	}

	public void setAccessBrowser(String accessBrowser) {
		this.accessBrowser = accessBrowser;
	}

	public String getUserCookie() {
		return userCookie;
	}

	public void setUserCookie(String userCookie) {
		this.userCookie = userCookie;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId == null ? null : webId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp == null ? null : accessIp.trim();
    }

    public String getAccessReferer() {
        return accessReferer;
    }

    public void setAccessReferer(String accessReferer) {
        this.accessReferer = accessReferer == null ? null : accessReferer.trim();
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId == null ? null : trackerId.trim();
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime == null ? null : accessTime.trim();
    }

	public String getAccessOperation() {
		return accessOperation;
	}

	public void setAccessOperation(String accessOperation) {
		this.accessOperation = accessOperation;
	}

	@Override
	public String toString() {
		return "AccessLogs [id=" + id + ", title=" + title + ", webId=" + webId
				+ ", userName=" + userName + ", domain=" + domain
				+ ", accessIp=" + accessIp + ", accessReferer=" + accessReferer
				+ ", accessOperation=" + accessOperation + ", trackerId="
				+ trackerId + ", accessTime=" + accessTime + ", accessBrowser="
				+ accessBrowser + ", userCookie=" + userCookie + "]";
	}

}