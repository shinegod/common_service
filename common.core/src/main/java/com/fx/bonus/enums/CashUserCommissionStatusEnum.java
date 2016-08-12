package com.fx.bonus.enums;


public enum CashUserCommissionStatusEnum {
	CASH_NO_PAY(1, "未支付"),
	CASH_PAY_SUCCESS(2, "已支付"),
	CASH_PAY_FAIL(3, "支付失败");
	int value;
	String text;
	CashUserCommissionStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static CashUserCommissionStatusEnum valueOf(int value){
		if(value == 1){
			return CASH_NO_PAY;
		} else if(value == 2){
			return CASH_PAY_SUCCESS;
		} else if(value == 3){
			return CASH_PAY_FAIL;
		}
		return null;
	}
}
