package com.fx.enums;


public enum CurrencyStatusEnum {

	USD("USD"),
	CNY("CNY");
	
	String text;
	CurrencyStatusEnum(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}

}


