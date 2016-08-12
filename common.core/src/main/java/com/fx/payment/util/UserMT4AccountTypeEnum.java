package com.fx.payment.util;

public enum UserMT4AccountTypeEnum {
	GENERAL(1, "一般"),
	CASH_DIVIDEND(2, "现金股利"),
	VIP(3, "VIP"),
	GUARANTEED(4, "保本保息");
	int value;
	String text;
	UserMT4AccountTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static UserMT4AccountTypeEnum valueOf(int value){
		if(value == 1){
			return GENERAL;
		} else if(value == 2){
			return CASH_DIVIDEND;
		} else if(value == 3){
			return VIP;
		} else if(value == 4){
			return GUARANTEED;
		}
		return null;
	}
}
