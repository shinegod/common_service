package com.fx.user.enums;


public enum CancellationStatusEnum {
	INIT_AUIT(1, "处理中"),
	AUIT_FAIL(2, "销户驳回"),
	AUIT_SUCCESS(3, "销户成功");
	int value;
	String text;
	CancellationStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static CancellationStatusEnum valueOf(int value){
		if(value == 1){
			return INIT_AUIT;
		} else if(value == 2){
			return AUIT_FAIL;
		} else if(value == 3){
			return AUIT_SUCCESS;
		} 
		return null;
	}
}
