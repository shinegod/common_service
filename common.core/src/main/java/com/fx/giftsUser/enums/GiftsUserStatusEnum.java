package com.fx.giftsUser.enums;


public enum GiftsUserStatusEnum {
	INIT(0),
	SUCCESS(1),
	FAIL(2);

	int value;
	GiftsUserStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static GiftsUserStatusEnum valueOf(int value){
		for(GiftsUserStatusEnum eValue : GiftsUserStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
