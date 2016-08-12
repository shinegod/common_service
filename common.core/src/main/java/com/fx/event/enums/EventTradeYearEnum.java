package com.fx.event.enums;


public enum EventTradeYearEnum {
	THREE(1,"3年以下"),
	FIVE(2,"3~5年"),
	TEN(3,"5~10年"),
	BIGGER_TEN(4,"10年以上");
	
	int value;
	String text;
	EventTradeYearEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EventTradeYearEnum valueOf(int value){
		for(EventTradeYearEnum eValue : EventTradeYearEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
