
package com.fx.trademsg.enums;


public enum TradeMsgTypeEnum {

	EVENT(1,"财经事件"),
	HOLIDAY(2,"世界各國假期");

	int value;
	String text;
	TradeMsgTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static TradeMsgTypeEnum valueOf(int value){
		for(TradeMsgTypeEnum eValue : TradeMsgTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}



