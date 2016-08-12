package com.fx.user.enums;

public enum UserStatusEnum {
	INIT(0),
	INIT_AUDIT(4),
	EA_AUDIT(5),
	PASS(2),
	WAIT_AUDIT(1),
	NO_PASS(3);
	int value;
	UserStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static UserStatusEnum valueOf(int value){
		if(value == 0){
			return INIT;
		} else if(value == 4){
			return INIT_AUDIT;
		} else if(value == 2){
			return PASS;
		} else if(value == 3){
			return NO_PASS;
		}else if(value == 1){
			return WAIT_AUDIT;
		}else if(value==5){
			return EA_AUDIT;
		}
		return null;
	}
}
