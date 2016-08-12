package com.fx.MT4.enums;


public enum CreditOperatorTypeEnum {
	
	BORROW(1),
	PAY_BACK(2);

	int value;
	CreditOperatorTypeEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static CreditOperatorTypeEnum valueOf(int value){
		for(CreditOperatorTypeEnum eValue : CreditOperatorTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
