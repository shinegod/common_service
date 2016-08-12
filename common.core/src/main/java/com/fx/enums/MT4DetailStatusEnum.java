package com.fx.enums;


public enum MT4DetailStatusEnum {

	FAIL(1,"失败"),
	SUCCESS(2,"成功");
	
	int value;
	String text;
	MT4DetailStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4DetailStatusEnum valueOf(int value){
		for(MT4DetailStatusEnum eValue:MT4DetailStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}


