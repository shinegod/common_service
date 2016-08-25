package com.fx.MT4.enums;


public enum MT4CmdEnum {
	BUY(0),
	SELL(1),
	BUY_LIMIT(2),
	SELL_LIMIT(3),
	BUY_STOP(4),
	SELL_STOP(5),
	Deposit(6),
	Credit(7);

	int value;
	MT4CmdEnum(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}

	public static MT4CmdEnum valueOf(int value){
		for(MT4CmdEnum eValue : MT4CmdEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
