package com.fx.user.enums;


public enum MT4TransferStatusEnum {
	NO_OPERATOR(0,"待审核"),
	SUCCESS(1,"审核通过转账成功"),
	FAIL(2,"审核通过转账失败"),
	WITHDRAWSUCCESS(3,"审核通过出金成功,入金失败"),
	NO_PASS(4,"审核未通过"),

	//--------------------华丽的分割线---------------------------
	NO_OPERATOR_AUTO(10,"待审核"),
	NO_INSUFFICIENT_BALANCE(11,"余额不足"),
	AMOUNT_NOT_TRANSFERRED(12,"金额未转出"),
	AMOUNT_NOT_ROLL(13,"金额未转入"),
	SUCCESS_AUTO(14,"审核通过转账成功"),
	SUCCESS_AUTO_FAILD(15,"审核通过转账成功修改状态失败"),


	//TODO 转账成功但是 净值为负数状态
	SUCCESS_AUTO_EQUITY_NEGATIVE(16,"转账成功净值为负数");
	//FAIL_AUTO(13,"审核通过转账失败"),
	//WITHDRAWSUCCESS_AUTO(14,"审核通过出金成功,入金失败"),
	//NO_PASS_AUTO(15,"审核未通过");

	int value;
	String text;
	MT4TransferStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4TransferStatusEnum valueOf(int value){
		for(MT4TransferStatusEnum eValue : MT4TransferStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
