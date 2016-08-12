package com.fx.user.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class Complaint extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private Integer complainttype;

    private Integer mt4account;

    private String phonenum;

    private String documentnumber;

    private BigDecimal disputeamount;

    private String title;

    private Integer status;
    
    private String content;

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

    public Integer getComplainttype() {
        return complainttype;
    }

    public void setComplainttype(Integer complainttype) {
        this.complainttype = complainttype;
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getDocumentnumber() {
        return documentnumber;
    }

    public void setDocumentnumber(String documentnumber) {
        this.documentnumber = documentnumber == null ? null : documentnumber.trim();
    }

    public BigDecimal getDisputeamount() {
        return disputeamount;
    }

    public void setDisputeamount(BigDecimal disputeamount) {
        this.disputeamount = disputeamount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}