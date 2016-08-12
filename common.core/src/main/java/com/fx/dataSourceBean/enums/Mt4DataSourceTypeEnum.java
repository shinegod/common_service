package com.fx.dataSourceBean.enums;

//用来显示mt4的类型
public enum Mt4DataSourceTypeEnum {
	ALL(0,"ALL"),
	Klimex(1, "Klimex"),
	MXT(2, "MXT");

	int value;
	String text;
	Mt4DataSourceTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static Mt4DataSourceTypeEnum valueOf(int value){
		if(value == 1){
			return Klimex;
		} else if(value == 2){
			return MXT;
		}
		return null;
	}
}
