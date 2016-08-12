package com.fx.bonus.enums;


public enum IBCommissionStatusEnum {
	SUBMITTED(0,"提交中"),
	NO_PAY(1, "未支付"),
	PAY_SUCCESS(2, "已支付"),
	PAY_FAIL(3, "支付失败"),
	REJECT(4, "拒绝");
	int value;
	String text;
	IBCommissionStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static IBCommissionStatusEnum valueOf(int value){
		if(value == 0){
			return SUBMITTED;
		} else if(value == 1){
			return NO_PAY;
		} else if(value == 2){
			return PAY_SUCCESS;
		} else if(value == 3){
			return PAY_FAIL;
		} else if(value == 4){
			return REJECT;
		}
		return null;
	}
}
