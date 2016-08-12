package com.fx.payment.enums;

/** 
* @ClassName: PayResultEnum 
* @Description: 支付结果枚举
* @author zhangpj
* @date 2013-12-2 下午11:26:53 
*  
*/
public enum PayResultEnum {
	PAY_SUCCESS("1"),//订单初始状态
	WAIT_PAY("2"),//支付成功
	REFUND_SUCCESS("3");//退款
	private String value;
	PayResultEnum(String value_){
		this.value = value_;
	}
	public String value(){
		return this.value;
	}
}
