package com.fx.user.enums;


public enum TradeQueryStatusEnum {
	QUERY_INIT(1, "处理中"),
	QUERY_FAIL(2, "不同意"),
	QUERY_SUCCESS(3, "同意");
	int value;
	String text;
	TradeQueryStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static TradeQueryStatusEnum valueOf(int value){
		if(value == 1){
			return QUERY_INIT;
		} else if(value == 2){
			return QUERY_FAIL;
		} else if(value == 3){
			return QUERY_SUCCESS;
		} 
		return null;
	}
}
