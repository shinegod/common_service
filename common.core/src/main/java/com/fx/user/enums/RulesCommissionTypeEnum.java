package com.fx.user.enums;


public enum RulesCommissionTypeEnum {
	PIPHAND_COMMISSION(1, "手数/点值返佣"),
	ADDITION_COMMISSION(2, "附加返佣");
	int value;
	String text;
	RulesCommissionTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static RulesCommissionTypeEnum valueOf(int value){
		for(RulesCommissionTypeEnum eDocType : RulesCommissionTypeEnum.values()){
			if(eDocType.getValue()==value){
				return eDocType;
			}
		}
		return null;
	}
}
