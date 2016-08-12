package com.fx.user.enums;


public enum LivingConditionsEnum {
	IPRIVATE(1, "自有"),
	MORTGAGE(2, "抵押"),
	LEASE(3, "租賃");
	int value;
	String text;
	LivingConditionsEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static LivingConditionsEnum valueOf(int value){
		if(value == 1){
			return IPRIVATE;
		} else if(value == 2){
			return MORTGAGE;
		} else if(value == 3){
			return LEASE;
		} 
		return null;
	}
}
