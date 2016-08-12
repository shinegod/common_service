package com.fx.enums;


public enum LanguageEnum {

	ZH_CN(1,"中文简体"),
	ZH(2,"繁體"),
	EN(3,"English");
	
	int value;
	String text;
	LanguageEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static LanguageEnum valueOf(int value){
		for(LanguageEnum eValue : LanguageEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}

