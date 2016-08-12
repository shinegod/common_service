package com.fx.user.enums;

/**
 * Created by Michael on 2015/6/29.
 */
public enum UserAuditStatusEnum {
    INIT_AUIT(0, "待审核"),
    AUIT_SUCCESS(1, "审核通过"),
    AUIT_FAIL(2, "审核不通过");
    int value;
    String text;
    UserAuditStatusEnum(int value, String text){
        this.value = value;
        this.text = text;
    }

    public int getValue(){
        return this.value;
    }

    public String getText(){
        return this.text;
    }

    public static UserAuditStatusEnum valueOf(int value){
        if(value == 1){
            return INIT_AUIT;
        } else if(value == 2){
            return AUIT_SUCCESS;
        } else if(value == 3){
            return AUIT_FAIL;
        }
        return null;
    }
}
