package com.fx.user.model;

import mybatis.framework.core.model.BaseValueObject;

public class InvestExperience extends BaseValueObject {
    private Integer id;

    private Integer userId;

    private Integer investmentExperienceCode;

    private Integer othersCode;

    private Integer tradingFrequencyCode;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvestmentExperienceCode() {
        return investmentExperienceCode;
    }

    public void setInvestmentExperienceCode(Integer investmentExperienceCode) {
        this.investmentExperienceCode = investmentExperienceCode;
    }

    public Integer getOthersCode() {
        return othersCode;
    }

    public void setOthersCode(Integer othersCode) {
        this.othersCode = othersCode;
    }

    public Integer getTradingFrequencyCode() {
        return tradingFrequencyCode;
    }

    public void setTradingFrequencyCode(Integer tradingFrequencyCode) {
        this.tradingFrequencyCode = tradingFrequencyCode;
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
}