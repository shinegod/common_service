package com.fx.payment.enums;


/** 
* @ClassName: UserAccountStatusEnum 
* @Description: 用户账户状态 
* @author zhangpj
* @date 2013-12-3 下午04:31:01 
*  
*/
public enum UserAccountStatusEnum {
	UN_ACTIVATE(0),	//未激活
	ACTIVATE(1);	//已激活
	private int value;
	UserAccountStatusEnum(int value_){
		this.value = value_;
	}
	public int getValue(){
		return this.value;
	}
}
