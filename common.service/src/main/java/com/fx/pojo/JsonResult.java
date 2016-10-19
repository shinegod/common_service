package com.fx.pojo;

/**
 * Created by Michael on 8/25/2016.
 */
public class JsonResult {

    private String msg;

    private String code;

    private String result;

    public JsonResult(String msg, String code, String result) {
        this.msg = msg;
        this.code = code;
        this.result = result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
