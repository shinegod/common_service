
package com.fx.news.enums;


public enum NewsTypeEnum {

	OFFICAL(1,"官方消息"),
	ACTIVITY(2,"活动讯息");
	
	int value;
	String text;
	NewsTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static NewsTypeEnum valueOf(int value){
		for(NewsTypeEnum eValue : NewsTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}



