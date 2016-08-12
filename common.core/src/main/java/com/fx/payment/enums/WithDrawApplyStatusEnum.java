package com.fx.payment.enums;

/** 
* @ClassName: WithDrawApplyStatusEnum 
* @Description: 出金申请单状态枚举
* @author zhangpj
* @date 2013-12-4 18:26:01 
*  
*/
public enum WithDrawApplyStatusEnum {
	INIT(10),
	VERIFY_PASS(20),
	VERIFY_UNPASS(30),
	WITHDRAW_SUCCESS(40),
	WITHDRAW_FAIL(50);
	
	private int value;
	
	WithDrawApplyStatusEnum(int _value){
		this.value = _value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static WithDrawApplyStatusEnum valueOf(int value){
		if(value == 10){
			return INIT;
		} else if (value == 20) {
			return VERIFY_PASS;
		} else if (value == 30) {
			return VERIFY_UNPASS;
		} else if (value == 40) {
			return WITHDRAW_SUCCESS;
		} else if (value == 50) {
			return WITHDRAW_FAIL;
		}
		return null;
	}
}
