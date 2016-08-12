package com.fx.leverage.enums;

/**
 * Created by Administrator on 2015/9/6.
 */
public enum LeverageStatusEnum {

    INIT(0),  //初始状态
    PASS(1),  //审核通过状态
    UNPASS(3), //审核未通过状态
    CANCLE(4); //用户取消状态
    private int value;

    LeverageStatusEnum(int _value){
        this.value = _value;
    }

    public int getValue(){
        return this.value;
    }

    public static LeverageStatusEnum valueOf(int value){
        if(value == 0){
            return INIT;
        } else if (value == 1) {
            return PASS;
        } else if (value == 3) {
            return UNPASS;
        } else if (value == 4) {
            return UNPASS;
        }
        return null;
    }
}
