/**
 *
 * date		2013-12-3
 * author	peijin.zhang
 * Copyright (c) 2013, zhangpj All Rights Reserved.
*/

package com.fx.payment.enums;
/**
 * ClassName:PayerTypeEnum
 * Function: 支付方类型枚举
 *
 * @author   peijin.zhang
 * @Date	 2013-12-3		下午05:19:39 
 */
public enum PayerTypeEnum {
	BANK_DIRECT_CONNECTION(1),	//银行直连
	THIRD_PARTY_PAYMENT(2),	//第三方支付
	ATM_DIRECT_PAYMENT(3),//银联
	ADMIN_OPERATION(4);//管理员后台操作
	
	private int value;
	
	PayerTypeEnum(int value_){
		this.value = value_;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static PayerTypeEnum valueOf(int value){
		if(value == 1){
			return BANK_DIRECT_CONNECTION;
		} else if (value == 2){
			return THIRD_PARTY_PAYMENT;
		} else if (value == 3){
			return ATM_DIRECT_PAYMENT;
		} else if (value == 4){
			return ADMIN_OPERATION;
		}
		return null;
	}
}

