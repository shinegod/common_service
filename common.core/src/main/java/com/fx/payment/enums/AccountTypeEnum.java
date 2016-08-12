package com.fx.payment.enums;


public enum AccountTypeEnum {
	
	REGISTER(0, "注册"),
	BING(1, "绑定");
	
	int value;
	String text;
	AccountTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static AccountTypeEnum valueOf(int value){
		for(AccountTypeEnum eValue : AccountTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
