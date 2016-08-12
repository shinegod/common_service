/**
 *
 * date		2013-12-3
 * author	peijin.zhang
 * Copyright (c) 2013, zhangpj All Rights Reserved.
*/

package com.fx.tradeAdjust.enums;

/**
 * ClassName:PayerTypeEnum
 * Function: 支付方类型枚举
 *
 * @author   peijin.zhang
 * @Date	 2013-12-3		下午05:19:39 
 */
public enum TradeAdjustEnum {
	ACOUNT_NO(1),	//非同名账户转账
	ACOUNT_FEE(2),	//退手续费
	ACOUNT_ZERO(3),//账户清零
	ACOUNT_GET(4),//赠金
	ACOUNT_MARK(5);//账户调整

	private int value;

	TradeAdjustEnum(int value_){
		this.value = value_;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static TradeAdjustEnum valueOf(int value){
		if(value == 1){
			return ACOUNT_NO;
		} else if (value == 2){
			return ACOUNT_FEE;
		} else if (value == 3){
			return ACOUNT_ZERO;
		} else if (value == 4){
			return ACOUNT_GET;
		} else if (value == 5){
			return ACOUNT_MARK;
		}
		return null;
	}
}

