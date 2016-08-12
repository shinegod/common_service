package com.fx.mt4TradeRecord.enums;


public enum MT4DespositWithdrawEnum {
	
	OPTION_SUCCESS(10,"成功"),
	OPTION_FAIL(-10,"连接服务器失败");
	int value;
	String text;
	MT4DespositWithdrawEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4DespositWithdrawEnum valueOf(int value){
		if(value == 10){
			return OPTION_SUCCESS;
		} else if (value == -10){
			return OPTION_FAIL;
		}
		return null;
	}
}
