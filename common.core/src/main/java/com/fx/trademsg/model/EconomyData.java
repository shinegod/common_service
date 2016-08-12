package com.fx.trademsg.model;

import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class EconomyData extends BaseValueObject {
    private Integer id;

    private Date date;

    private String time;

    private String event;

    private Integer significance;

    private String previousValue;

    private String estimatedValue;

    private String presentValue;

    private Integer language;

    private Date createTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public Integer getSignificance() {
        return significance;
    }

    public void setSignificance(Integer significance) {
        this.significance = significance;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue == null ? null : previousValue.trim();
    }

    public String getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(String estimatedValue) {
        this.estimatedValue = estimatedValue == null ? null : estimatedValue.trim();
    }

    public String getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(String presentValue) {
        this.presentValue = presentValue == null ? null : presentValue.trim();
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}