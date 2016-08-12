package com.fx.user.enums;


public enum ComplaintStatusEnum {
	COMPLAINT_INIT(1, "处理中"),
	COMPLAINT_FAIL(2, "投诉驳回"),
	COMPLAINT_SUCCESS(3, "投诉成功");
	int value;
	String text;
	ComplaintStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static ComplaintStatusEnum valueOf(int value){
		if(value == 1){
			return COMPLAINT_INIT;
		} else if(value == 2){
			return COMPLAINT_FAIL;
		} else if(value == 3){
			return COMPLAINT_SUCCESS;
		} 
		return null;
	}
}
