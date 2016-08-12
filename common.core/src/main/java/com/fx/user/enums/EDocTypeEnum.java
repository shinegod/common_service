package com.fx.user.enums;


public enum EDocTypeEnum {
	IDENTITY_PROVE(1, "身份证明"),
	ADDRESS_PROVE(2, "地址证明"),
	WITHDRAW_ACCOUNT(3, "出金银行账户信息"),
	CREDIT_CARD_FRONT(4, "信用卡信息-正面"),
	CREDIT_CARD_BACK(5, "信用卡信息-反面"),
	ADDMONEY_ACCOUNT(6, "入金银行账户信息"),
	PAYMENT_AUTHORIZATION(7, "付款授权书"),
	BANK_RETURN(8, "汇款回执单"),
	PASSPORT(9, "护照"),
	DRIVER_lICENSE(10, "驾驶证"),
	CONTRACT(11, "合同"),
	COMMISSION_REPORT(16, "IB佣金规则报表"), //重要字段 非必需不允许展示出
	COMMISSION_INNER_REPORT(17, "内部佣金规则报表"), //重要字段 非必需不允许展示出
	VOLUME_COMMISSION_REPORT(101, "交易量/佣金报表"),
	//TODO 后续添加
	BANK_CARD(22,"银行卡"),
	ID_CARD_A(33,"身份证正面"),
	ID_CARD_B(44,"身份证负面");

	
	int value;
	String text;
	EDocTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static EDocTypeEnum valueOf(int value){
		for(EDocTypeEnum eDocType : EDocTypeEnum.values()){
			if(eDocType.getValue()==value){
				return eDocType;
			}
		}
		return null;
	}
}
