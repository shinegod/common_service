package com.fx.payment.enums;

/** 
* @ClassName: TradeStatusEnum 
* @Description: 交易的状态
* @author zhangpj
* @date 2013-12-2 下午11:28:02 
*  
*/
public enum TradeStatusEnum {
	ADD_MONEY_INIT(110),						//入金初始状态
	ADD_MONEY_HANDLE(100),                      //待支付状态已经离开平台
	ADD_MONEY_BANK_SUCCESS_WAIT_MT4(120),		//银行支付成功，待MT4入金

	ADD_MONEY_SUCCESS(130),						//入金成功
	ADD_MONEY_FAIL(140),						//审核失败
	ADD_MONEY_BANK_FAIL(150),                   //第三方支付失败
	//ADD_MONEY_STAGING_FORMS(160),             //暂存状态

	ADD_MONEY_SUCCESS_MT4FAIL(170),             //转账成功入mt4失败

	ADD_MONEY_SUCCESSMT4_DBFAIL(190),           //入金成功更新数据库失败

	ADD_MONEY_STAGING_FORMS(160),               //暂存状态

	//出金
	WITHDRAW_MONEY_INIT(210),					//出金初始状态
	WITHDRAW_MONEY_MT4_SUCCESS_WAIT_BANK(220),	//MT4出金成功，待银行确认

	WITHDRAW_MONEY_SUCCESS(230),	//出金审核成功
	WITHDRAW_MONEY_FAIL(240);		//出金审核失败


	private int value;
	
	TradeStatusEnum(int value_){
		this.value = value_;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static TradeStatusEnum valueOf(int value){
		if(value == 110){
			return ADD_MONEY_INIT;
		} else if (value == 120){
			return ADD_MONEY_BANK_SUCCESS_WAIT_MT4;
		} else if (value == 130){
			return ADD_MONEY_SUCCESS;
		} else if(value == 210){
			return WITHDRAW_MONEY_INIT;
		} else if(value == 220){
			return WITHDRAW_MONEY_MT4_SUCCESS_WAIT_BANK;
		} else if(value == 230){
			return WITHDRAW_MONEY_SUCCESS;
		} else if (value == 140){
			return ADD_MONEY_FAIL;
		} else if (value == 150){
			return ADD_MONEY_BANK_FAIL;
		} else if (value == 160){
			return ADD_MONEY_STAGING_FORMS;
		}else if (value == 100){
			return ADD_MONEY_HANDLE;
		}
		return null;
	}
}
