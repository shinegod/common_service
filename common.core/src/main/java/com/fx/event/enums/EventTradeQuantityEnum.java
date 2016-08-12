package com.fx.event.enums;


public enum EventTradeQuantityEnum {
	LESS_100(1,"100手以下"),
	LESS_300(2,"100~300手"),
	LESS_500(3,"300~500手"),
	LESS_1000(4,"500~1000手"),
	MORE_1000(5,"1000手以上");
	
	int value;
	String text;
	EventTradeQuantityEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EventTradeQuantityEnum valueOf(int value){
		for(EventTradeQuantityEnum eValue : EventTradeQuantityEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
