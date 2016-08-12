package com.fx.task.enums;


public enum TaskStatusEnum {

//
//	WHEN 110 THEN
//	-2  --->未支付
//	WHEN 120 THEN
//	0  ---> 提交中
//	WHEN 130 THEN
//	1  --->审核通过
//	WHEN 140 THEN
//	3 --->拒绝
//	WHEN 150 THEN
//	2 --->未支付
//	WHEN 100 THEN
//	-3 --->已跳转支付

	ALL_STATUS(-1,"所有状态"),
	SUBMITTED(0,"提交中"),
	ACCEPTED(1,"通过审核"),
	FAIL(2,"失败"),
	REJECTED(3,"拒绝"),
	CANCEL(4,"取消"),

	NOPAY(-2,"未支付"),
	HAVING(-3,"已跳转支付");
	int value;
	String text;
	TaskStatusEnum(int value, String text){
		this.value = value;
		this.text = text;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static TaskStatusEnum valueOf(int value){
		for(TaskStatusEnum eValue : TaskStatusEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}



