package com.fx.ip.model;

import mybatis.framework.core.model.BaseValueObject;

/**
 * Created by Michael on 8/23/2016.
 */
public class Authorization extends BaseValueObject {

    private String app_id;

    private String app_secret;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }
}
