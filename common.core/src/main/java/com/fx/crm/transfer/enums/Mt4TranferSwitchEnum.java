package com.fx.crm.transfer.enums;


public enum Mt4TranferSwitchEnum {
	
	MT_4_TRANFER_SWITCH_ON(0,"启动"),
	MT_4_TRANFER_SWITCH_OFF(1,"未启动");

	int value;
	String text;
	Mt4TranferSwitchEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static Mt4TranferSwitchEnum valueOf(int value){
		for(Mt4TranferSwitchEnum eValue : Mt4TranferSwitchEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
