package com.fx.user.enums;


public enum MT4TransferLogStatusEnum {
	
	SUCCESS(1,"成功"),
	FAIL(2,"失败"),
	WITHDRAWSUCCESS(3,"出金成功"),
	DEPOSITFAIL(4,"入金失败");
	
	int value;
	String text;
	MT4TransferLogStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4TransferLogStatusEnum valueOf(int value){
		for(MT4TransferLogStatusEnum eValue : MT4TransferLogStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
