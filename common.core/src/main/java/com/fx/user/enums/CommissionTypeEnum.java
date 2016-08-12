package com.fx.user.enums;


public enum CommissionTypeEnum {
	NO_COMMISSION(0, "未返佣"),
	COMMISSION(1, "返佣");
	int value;
	String text;
	CommissionTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static CommissionTypeEnum valueOf(int value){
		for(CommissionTypeEnum eDocType : CommissionTypeEnum.values()){
			if(eDocType.getValue()==value){
				return eDocType;
			}
		}
		return null;
	}
}
