package com.fx.dataSourceBean.enums;

//用来区别数据源的类型，目前暂时分为模拟和真实
public enum DataSourceTypeEnum {
	DEMO_ACCOUNT(1, "模拟数据源"),
	PERSONAL_ACCOUNT(2, "真实数据源");

	int value;
	String text;
	DataSourceTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static DataSourceTypeEnum valueOf(int value){
		if(value == 1){
			return DEMO_ACCOUNT;
		} else if(value == 2){
			return PERSONAL_ACCOUNT;
		}
		return null;
	}
}
