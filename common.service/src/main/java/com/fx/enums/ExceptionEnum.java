package com.fx.enums;

/**
 * Created by Michael on 8/24/2016.
 */
public enum ExceptionEnum {

    LACK_PARAM("failure","E00001"),          // 缺少app_id
    PERMISSION_DENIED("failure", "E00002"),   // 没有权限
    QUERY_FAILURE("failure", "E00003"),             // 查询失败
    OPERATE_FAILURE("failure", "E00004"),         // 操作失败
    PARAMS_TOO_LONG("failure", "E00005");   // 参数过长

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
