package com.fx.user.enums;


public enum LeadsFlagEnum {
	LEADS_CUS(1, "leads升级为直客");


	int value;
	String text;
	LeadsFlagEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static LeadsFlagEnum valueOf(int value){
		for(LeadsFlagEnum eDocType : LeadsFlagEnum.values()){
			if(eDocType.getValue()==value){
				return eDocType;
			}
		}
		return null;
	}
}
