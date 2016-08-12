package com.fx.payment.enums;


public enum ProjectTypeEnum {
	
	COMMON(1, "一般"),
	CASH_DIVIDEND(2, "现金股利"),
	VIP(3, "VIP"),
	GUARANTEED(4, "外汇活存"),
	VVIP(5,"VVIP");
	
	int value;
	String text;
	ProjectTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static ProjectTypeEnum valueOf(int value){
		for(ProjectTypeEnum eValue : ProjectTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
