package com.fx.user.model;

import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class Content extends BaseValueObject {

    private String label = "";

    private String value = "";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}