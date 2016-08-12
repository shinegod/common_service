package com.fx.event.enums;


public enum EventTypeEnum {
	
	JINSONG(1,"敬松课程"),
	EA(2,"EA高手"),
	BAO_BEN_BAO_XI(3,"外汇活存"),
	CAO_PAN_SHOU(4,"操盤手"),
	XIAN_JIN_GU_LI(5,"現金股利"),
	VIP(6,"VIP"),
	VVIP(7,"VVIP");
	
	int value;
	String text;
	EventTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EventTypeEnum valueOf(int value){
		for(EventTypeEnum eValue : EventTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
