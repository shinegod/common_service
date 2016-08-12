package com.fx.user.enums;

public enum UserAdminTypeEnum {
	Customer(0),
	Administrator(1);
	int value;
	UserAdminTypeEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static UserAdminTypeEnum valueOf(int value){
		if(value == 0){
			return Customer;
		} else if(value == 1){
			return Administrator;
		}
		return null;
	}
}
