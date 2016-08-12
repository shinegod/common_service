package com.fx.mt4TradeRecord.model;

import mybatis.framework.core.model.BaseValueObject;

public class Mt4Config extends BaseValueObject {
    private Integer config;

    private Integer valueInt;

    private String valueStr;

    public Integer getConfig() {
        return config;
    }

    public void setConfig(Integer config) {
        this.config = config;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr == null ? null : valueStr.trim();
    }
}