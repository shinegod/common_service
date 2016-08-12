package com.fx.bonus.enums;


public enum SourceEnum {
	SOURCE_VVIP(0, "VVIP"),
	SOURCE_VIP(1, "VIP"),
	SOURCE_COMMON(2, "普通用户"),
	SOURCE_CASH(3, "现金股利"),
	SOURCE_GUARAN(4, "外汇活存");
	int value;
	String text;
	SourceEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static SourceEnum valueOf(int value){
		if(value == 0){
			return SOURCE_VVIP;
		} else if(value == 1){
			return SOURCE_VIP;
		} else if(value == 2){
			return SOURCE_COMMON;
		} else if(value == 3){
			return SOURCE_CASH;
		}else if(value == 4){
			return SOURCE_GUARAN;
		}
		return null;
	}
}
