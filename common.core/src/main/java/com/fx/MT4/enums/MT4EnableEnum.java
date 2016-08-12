package com.fx.MT4.enums;


public enum MT4EnableEnum {
	ENABLE(1),//启动
	DO_NOT_ENABLE(0);//不启动
	
	int value;
	MT4EnableEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static MT4EnableEnum valueOf(int value){
		for(MT4EnableEnum eValue : MT4EnableEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
