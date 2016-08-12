package com.fx.bonus.enums;


public enum EaManagerStatusEnum {
	EA_CHECK(1, "未审核"),
	EA_CHECK_PASS(2, "已审核"),
	EA_CHECK_NOPASS(3, "未通过");
	int value;
	String text;
	EaManagerStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EaManagerStatusEnum valueOf(int value){
		if(value == 1){
			return EA_CHECK;
		} else if(value == 2){
			return EA_CHECK_PASS;
		} else if(value == 3){
			return EA_CHECK_NOPASS;
		}
		return null;
	}
}
