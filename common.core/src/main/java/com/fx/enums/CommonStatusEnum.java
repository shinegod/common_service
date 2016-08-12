package com.fx.enums;


import com.fx.enums.CommonStatusEnum;

public enum CommonStatusEnum {

	NOMAL(1,"发布中"),
	DELET(-1,"删除状态");
	
	int value;
	String text;
	CommonStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static CommonStatusEnum valueOf(int value){
		for(CommonStatusEnum eValue : CommonStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}


