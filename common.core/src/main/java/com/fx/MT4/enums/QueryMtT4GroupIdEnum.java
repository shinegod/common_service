package com.fx.MT4.enums;


public enum QueryMtT4GroupIdEnum {
	DEMO(33),
	LIVE(0);
	
	//以后Live Server多了之后， 不能这样指定服务器
	
	
	int value;
	QueryMtT4GroupIdEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static QueryMtT4GroupIdEnum valueOf(int value){
		for(QueryMtT4GroupIdEnum eValue : QueryMtT4GroupIdEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
