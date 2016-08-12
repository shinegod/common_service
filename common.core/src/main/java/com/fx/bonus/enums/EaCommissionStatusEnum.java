package com.fx.bonus.enums;


public enum EaCommissionStatusEnum {
	EA_NO_PAY(1, "未支付"),
	EA_PAY_SUCCESS(2, "已支付"),
	EA_PAY_FAIL(3, "支付失败");
	int value;
	String text;
	EaCommissionStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EaCommissionStatusEnum valueOf(int value){
		if(value == 1){
			return EA_NO_PAY;
		} else if(value == 2){
			return EA_PAY_SUCCESS;
		} else if(value == 3){
			return EA_PAY_FAIL;
		}
		return null;
	}
}
