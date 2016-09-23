package com.fx.enums;

/**
 * Created by Michael on 8/25/2016.
 */
public enum SuccessEnum {

    QUERY_SUCCESS("query success", "S00000"),
    OPERATE_SUCCESS("operate success", "S00001");

    String code;

    String msg;

    SuccessEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static String getMsg(String code) {
        for (SuccessEnum successEnum : SuccessEnum.values()) {
            if (successEnum.getCode().equals(code)) {
                return successEnum.msg;
            }
        }
        return null;
    }

}
