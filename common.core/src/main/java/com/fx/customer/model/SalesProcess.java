package com.fx.customer.model;

import mybatis.framework.core.model.BaseValueObject;

public class SalesProcess extends BaseValueObject {
    private Integer id;

    private String processName;

    private String status = "((0))";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}