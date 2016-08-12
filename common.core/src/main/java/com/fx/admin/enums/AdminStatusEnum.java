/**
 * com.fx.payment.enums.PayerTypeEnum.java
 *
 * date		2013-12-3
 * author	peijin.zhang
 * Copyright (c) 2013, zhangpj All Rights Reserved.
*/

package com.fx.admin.enums;
/**
 * @author   peijin.zhang
 * @Date	 2013-12-3		下午05:19:39 
 */
public enum AdminStatusEnum {
	NORMAL(1),	
	STOP(2);		
	
	private int value;
	
	AdminStatusEnum(int value_){
		this.value = value_;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static AdminStatusEnum valueOf(int value){
		if(value == 1){
			return NORMAL;
		} else if (value == 2){
			return STOP;
		}
		return null;
	}
}

