package com.fx.MT4.enums;


public enum MT4GroupIdEnum {
	//NZGFTA1(24),//A10,代表意思为:专用组，不对网站开放
	M_VFX_PACF_USD(27,"M_VFX_PACF_USD"),//A40,代表意思为:一般
	M_VFX_PACFV_USD(32,"M_VFX_PACFV_USD"),//B400,代表意思为:一般
	M_VFX_PAC_P_USD(28,"M_VFX_PAC_P_USD"),//A50,代表意思为:现金股利
	M_VFX_PAC_USD(29,"M_VFX_PAC_USD"),//B100,代表意思为:外汇活存
	M_VFX_PAC_AUD(26,"M_VFX_PAC_AUD"),//A30,代表意思为:VIP
	M_VFX_PAC_P_AUD(31,"M_VFX_PAC_P_AUD");//B300,代表意思为:VIP
	
	
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
