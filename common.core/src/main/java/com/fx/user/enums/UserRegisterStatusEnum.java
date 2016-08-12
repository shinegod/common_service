package com.fx.user.enums;

public enum UserRegisterStatusEnum {
	INIT(0),
	DATANOALL(1),
	ACTIVATED(2);
	int value;
	UserRegisterStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static UserRegisterStatusEnum valueOf(int value){
		if(value == 2){
			return ACTIVATED;
		}else if(value==1){
			return DATANOALL;
		}
		return INIT;
	}
}
