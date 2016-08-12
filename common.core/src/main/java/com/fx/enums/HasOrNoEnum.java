package com.fx.enums;

/** 
* @ClassName: IsDelEnum 
* @Description: 是否删除的通用枚举
* @author zhangpj
* @date 2013-12-2 下午11:24:00 
*  
*/
public enum HasOrNoEnum {
	YES(1),
	NO(0);
	int value;
	HasOrNoEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static HasOrNoEnum valueOf(int value){
		if(value == 1){
			return YES;
		} else if(value == 0){
			return NO;
		}
		return null;
	}
}
