package com.fx.lwork.model;

import mybatis.framework.core.model.BaseValueObject;

public class WorkUser extends BaseValueObject {
    private Integer id;

    private String login;

    private String customerName;

    private String cardNo;

    private String paperNo;

    private String filePathPaper;

    private String filePathCard;

    private Integer status;

    private String createtime;
    
    private String mark;
    
    //file_local_path_paper,file_local_path_card
    private String fileLocalPathPaper;
    private String fileLocalPathCard;
    
    //TODO后续添加字段
    private Integer uid;
    

    public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getFileLocalPathPaper() {
		return fileLocalPathPaper;
	}

	public void setFileLocalPathPaper(String fileLocalPathPaper) {
		this.fileLocalPathPaper = fileLocalPathPaper;
	}

	public String getFileLocalPathCard() {
		return fileLocalPathCard;
	}

	public void setFileLocalPathCard(String fileLocalPathCard) {
		this.fileLocalPathCard = fileLocalPathCard;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getPaperNo() {
        return paperNo;
    }

    public void setPaperNo(String paperNo) {
        this.paperNo = paperNo == null ? null : paperNo.trim();
    }

    public String getFilePathPaper() {
        return filePathPaper;
    }

    public void setFilePathPaper(String filePathPaper) {
        this.filePathPaper = filePathPaper == null ? null : filePathPaper.trim();
    }

    public String getFilePathCard() {
        return filePathCard;
    }

    public void setFilePathCard(String filePathCard) {
        this.filePathCard = filePathCard == null ? null : filePathCard.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}