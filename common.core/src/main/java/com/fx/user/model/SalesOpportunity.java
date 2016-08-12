package com.fx.user.model;

import com.fx.admin.model.Admin;
import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

public class SalesOpportunity extends BaseValueObject {
    private Integer id;

    private String title;

    private BigDecimal expectedAmount;

    private String saleProcess;

    private String expectedDeal;

    private String comment;

    private Integer userId;

    private Integer saleId;

    private String creator;

    private Date createDate;

    private String updater;

    private Date updateTime;

    private Integer creatorId;

    private Integer updaterId;

    Admin admin;

    UserRegister userRegister;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
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

    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public String getSaleProcess() {
        return saleProcess;
    }

    public void setSaleProcess(String saleProcess) {
        this.saleProcess = saleProcess == null ? null : saleProcess.trim();
    }

    public String getExpectedDeal() {
        return expectedDeal;
    }

    public void setExpectedDeal(String expectedDeal) {
        this.expectedDeal = expectedDeal == null ? null : expectedDeal.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
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
}