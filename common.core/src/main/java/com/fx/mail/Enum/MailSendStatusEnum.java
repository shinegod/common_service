package com.fx.mail.Enum;


public enum MailSendStatusEnum {
	
	SUCCESS(1),
	FAIL(0);

	int value;
	MailSendStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public static MailSendStatusEnum valueOf(int value){
		for(MailSendStatusEnum eValue : MailSendStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}
}
