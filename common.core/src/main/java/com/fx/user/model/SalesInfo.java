package com.fx.user.model;

import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class SalesInfo extends BaseValueObject {
    private Integer id;

    private Date interviewDate;

    private Date nextInterviewDate;

    private String isEmergency;

    private String salesRecord;

    private Integer userId;

    private Integer saleId;

    private String creator;

    private Date createDate;

    private String updater;

    private Date updateTime;

    private Integer creatorId;

    private Integer updaterId;

    private String interview_type;

    private Integer isLast;

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(String isEmergency) {
        this.isEmergency = isEmergency == null ? null : isEmergency.trim();
    }

    public String getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(String salesRecord) {
        this.salesRecord = salesRecord == null ? null : salesRecord.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public String getInterview_type() {
        return interview_type;
    }

    public void setInterview_type(String interview_type) {
        this.interview_type = interview_type;
    }

    public Date getNextInterviewDate() {
        return nextInterviewDate;
    }

    public void setNextInterviewDate(Date nextInterviewDate) {
        this.nextInterviewDate = nextInterviewDate;
    }
}