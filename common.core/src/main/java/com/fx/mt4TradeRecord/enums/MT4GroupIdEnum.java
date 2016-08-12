package com.fx.mt4TradeRecord.enums;


public enum MT4GroupIdEnum {
	//NZGFTA1(24),//A10,代表意思为:专用组，不对网站开放
	COMMON_A(27,"NZGFTA4"),//A40,代表意思为:一般
	COMMON_B(32,"NZGFTB4"),//B400,代表意思为:一般
	CASH_DIVIDEND(28,"NZGFTA5"),//A50,代表意思为:现金股利
	GUARANTEED(29,"NZGFTB1"),//B100,代表意思为:外汇活存
	VIP_A(26,"NZGFTA3"),//A30,代表意思为:VIP
	VIP_B(31,"NZGFTB3"),//B300,代表意思为:VIP
	VVIP_A(25,"NZGFTA2"),//A20,代表意思为:xxx
	VVIP_B(30,"NZGFTB2"),//B200,代表意思为:xxx
	DEMO(33,"demoNZGFT");//demo,代表意思为:demo组
	
	
	int value;
	String text;
	MT4GroupIdEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4GroupIdEnum valueOf(int value){
		for(MT4GroupIdEnum eValue : MT4GroupIdEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
