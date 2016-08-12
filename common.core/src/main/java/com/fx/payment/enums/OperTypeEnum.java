/**
 *
 * date		2013-12-3
 * author	peijin.zhang
 * Copyright (c) 2013, zhangpj All Rights Reserved.
*/

package com.fx.payment.enums;
/**
 * OperTypeEnum
 * Function: 支付方类型枚举
 *
 * @author   peijin.zhang
 * @Date	 2013-12-3		下午05:39:39 
 */
public enum OperTypeEnum {
	ADD_MONEY(1),			//入金
	WITHDRAW_MONEY(2);		//出金
	
	private int value;
	
	OperTypeEnum(int value_){
		this.value = value_;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static OperTypeEnum valueOf(int value){
		if(value == 1){
			return ADD_MONEY;
		} else if (value == 2){
			return WITHDRAW_MONEY;
		}
		return null;
	}
}

