package com.fx.mt4TradeRecord.enums;


public enum MT4PasswordRequireEnum {
	REQUIRE("1"),//需要密码--live账户
	NOT_REQUIRE("0");//不需要密码--demo账户
	String value;
	MT4PasswordRequireEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public static MT4PasswordRequireEnum valueOf(int value){
		for(MT4PasswordRequireEnum eValue : MT4PasswordRequireEnum.values()){
			if(eValue.getValue().equals(value)){
				return eValue;
			}
		}
		return null;
	}
}
