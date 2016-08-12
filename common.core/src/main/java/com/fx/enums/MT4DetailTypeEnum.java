package com.fx.enums;


public enum MT4DetailTypeEnum {

	DEPOSIT(1,"入金"),
	WITHDRAW(2,"出金"),
	BONUS(3,"红利金"),
	IBCOMM(4,"IB佣金"),
	EACOMM(5,"EA佣金"),
	CASHCOMM(6,"现金股利佣金"),
	TRANSFER(7,"转账成功");

	int value;
	String text;
	MT4DetailTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4DetailTypeEnum valueOf(int value){
		for(MT4DetailTypeEnum eValue:MT4DetailTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}


