package com.fx.logs.model;

import mybatis.framework.core.model.BaseValueObject;

/**
 * Created by Michael on 8/24/2016.
 */
public class Logs extends BaseValueObject {

    private String id;

    private String app_id;

    private String req_url;

    private String get_params;

    private String req_ip;

    private String post_params;

    private String req_method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getUrl() {
        return req_url;
    }

    public void setUrl(String req_url) {
        this.req_url = req_url;
    }

    public String getGet_params() {
        return get_params;
    }

    public void setGet_params(String get_params) {
        this.get_params = get_params;
    }

    public String getIp() {
        return req_ip;
    }

    public void setIp(String req_ip) {
        this.req_ip = req_ip;
    }

    public String getPost_params() {
        return post_params;
    }

    public void setPost_params(String post_params) {
        this.post_params = post_params;
    }

    public String getReq_method() {
        return req_method;
    }

    public void setReq_method(String req_method) {
        this.req_method = req_method;
    }
}
