package com.fx.payment.enums;

/** 
* @ClassName: PayerEnum 
* @Description: 支付方枚举
* @author zhangpj
* @date 2013-12-2 下午11:26:01 
*  
*/
public enum PayerEnum {
	_99Bill(1);
	private int value;
	
	PayerEnum(int _value){
		this.value = _value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static PayerEnum valueOf(int value){
		if(value == 1){
			return _99Bill;
		}
		return null;
	}
}
