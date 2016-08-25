package com.fx.enums;

/**
 * Created by Michael on 8/24/2016.
 */
public enum ExceptionEnum {

    LACK_PARAM("app_id is required","E00001"),
    PERMISSION_DENIED("permission denied", "E00002");

    String code;
    String msg;

    ExceptionEnum(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static String getMsg(String code) {
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.getCode().equals(code)) {
                return exceptionEnum.msg;
            }
        }
        return null;
    }

}
