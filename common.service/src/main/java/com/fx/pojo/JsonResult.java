package com.fx.pojo;

/**
 * Created by Michael on 8/25/2016.
 */
public class JsonResult {

    private String msg;

    private String code;

    public JsonResult(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
