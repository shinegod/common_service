package com.fx.payment.util;

public enum UserMT4AccountStatusEnum {
	AUDIT_WRITE(0, "处理中"),
	PASS(1, "审核通过"),
	NO_PASS(2, "审核未通过");
	int value;
	String text;
	UserMT4AccountStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static UserMT4AccountStatusEnum valueOf(int value){
		if(value == 0){
			return AUDIT_WRITE;
		}else if(value == 1){
			return PASS;
		}else if(value == 2){
			return NO_PASS;
		}
		return null;
	}
}
