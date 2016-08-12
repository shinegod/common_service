package com.fx.user.model;

import mybatis.framework.core.model.BaseValueObject;

public class Cancellation extends BaseValueObject {
    private Integer id;

    private Integer uid;

    private String cancellationreason;

    private Integer status;

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

    public String getCancellationreason() {
        return cancellationreason;
    }

    public void setCancellationreason(String cancellationreason) {
        this.cancellationreason = cancellationreason == null ? null : cancellationreason.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}