package com.fx.MT4.enums;

/**
 * 设置mt4trader中Comment，用于区分转账(转入，转出)、出金、入金、返佣
 * @author xiuling
 *
 */
public enum MT4OperateTypeEnum {
	
	TRANSFER_OUT(1,"TRANSFER_OUT"),
	TRANSFER_IN(2,"TRANSFER_IN"),
	DEPOSIT(3,"DEPOSIT"),
	WITHDRAWAL(4,"WITHDRAWAL"),
	RETURNCOMMISSION(5,"RETURNCOMMISSION");
	
	int value;
	String text;
	
	MT4OperateTypeEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static MT4OperateTypeEnum valueOf(int value){
		for(MT4OperateTypeEnum eValue : MT4OperateTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
	
}
