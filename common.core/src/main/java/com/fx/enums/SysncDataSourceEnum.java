package com.fx.enums;

public enum SysncDataSourceEnum {

	MXT(16, "mxt"),
	VFXUK(6, "vfxuk"),
	VFXAUS(7, "vfxaus");
	
	int value;
	
	String text;
	
	SysncDataSourceEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static SysncDataSourceEnum valueOf(int value){
		if(value == 16) {
			return MXT;
		} else if(value == 6) {
			return VFXUK;
		} else if(value == 7) {
			return VFXAUS;
		}
		return null;
	}
}
