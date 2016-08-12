package com.fx.enums;

public enum IsDelEnum {
	YES(1),//有
	NO(0);//没有
	int value;
	IsDelEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static IsDelEnum valueOf(int value){
		if(value == 1){
			return YES;
		} else if(value == 0){
			return NO;
		}
		return null;
	}
}
