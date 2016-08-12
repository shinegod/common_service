package com.fx.customer.model;

import mybatis.framework.core.model.BaseValueObject;

public class CustSource extends BaseValueObject {
    private Integer id;

    private String sourceName;

    private String status = "((0))";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}