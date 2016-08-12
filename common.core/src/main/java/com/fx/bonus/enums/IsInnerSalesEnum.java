package com.fx.bonus.enums;


public enum IsInnerSalesEnum {
	INNER_SALE(1, "销售"),
	OUTER_USER(2, "客户");
	int value;
	String text;
	IsInnerSalesEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static IsInnerSalesEnum valueOf(int value){
		if(value == 1){
			return INNER_SALE;
		} else if(value == 2){
			return OUTER_USER;
		}
		return null;
	}
}
