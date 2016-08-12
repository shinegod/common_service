package com.fx.payment.util;

public enum MT4AccountTypeEnum {
	DEMO_STADARD(10, "模拟账户标准类型"),
	DEMO_MINI(11, "模拟账户迷你类型"),
	DEMO_EA(12, "模拟账户EA类型"),
	DEMO_PRO(13, "模拟账户专业类型"),
	STANDARD(20, "真实账户标准类型"),
	MINI(21, "真实账户迷你类型"),
	PRO(22, "真实账户专业类型"),
	EA(23, "真实账户EA类型"),
	MONEY_MANAGER(24, "真实账户资金管理人"),
	GENERAL(25, "一般"),
	CASH_DIVIDEND(26, "现金股利"),
	VIP(27, "VIP"),
	GUARANTEED(28, "保本保息");
	int value;
	String text;
	MT4AccountTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static MT4AccountTypeEnum valueOf(int value){
		if(value == 10){
			return DEMO_STADARD;
		}else if(value == 11){
			return DEMO_MINI;
		}else if(value == 12){
			return DEMO_EA;
		}else if(value == 13){
			return DEMO_PRO;
		}else if(value == 20){
			return STANDARD;
		} else if(value == 21){
			return MINI;
		} else if(value == 22){
			return PRO;
		} else if(value == 23){
			return EA;
		} else if(value == 24){
			return MONEY_MANAGER;
		} else if(value == 25){
			return GENERAL;
		} else if(value == 26){
			return CASH_DIVIDEND;
		} else if(value == 27){
			return VIP;
		} else if(value == 28){
			return GUARANTEED;
		}
		return null;
	}
}
