package com.fx.task.enums;

public enum SupportTypeEnum {

	SUPPORT_SALES("p1576","SUPPORT_SALES"),//内部用户
	SUPPORT_IB("p1577","SUPPORT_IB"),//外部用户
	SUPPORT_REPORT304("p1578","SUPPORT_REPORT304"),//挂单查询
	SUPPORT_REPORT305("p1579","SUPPORT_REPORT305"),//综合报表
	SUPPORT_REPORT4002("p1582","SUPPORT_REPORT4002"),//新账户报表
	SUPPORT_REPORT4003("p1583","SUPPORT_REPORT4003"),//持仓查询
	SUPPORT_REPORT4004("p1586","SUPPORT_REPORT4004"),//客户月报表
	SUPPORT_REPORT4001("p1584","SUPPORT_REPORT4001"),//交易历史
	SUPPORT_REPORT311("p1585","SUPPORT_REPORT311"),//止损止盈查询
	SUPPORT_REPORT308("p1586","SUPPORT_REPORT308"),//存取款调整
	SUPPORT_CUSTOMER("p1588","SUPPORT_CUSTOMER"),//客户资源
	SUPPORT_LEADS("p1629","SUPPORT_LEADS"),//LEADS
	SUPPORT_TASK("p1589","SUPPORT_TASK"),//任务管理
	SUPPORT_USER("p1590","SUPPORT_USER");//账户管理

	String value;
	String text;
	SupportTypeEnum(String value, String text){
		this.value = value;
		this.text = text;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getText(){
		return this.text;
	}
	
	public static SupportTypeEnum toValue(String value){
		for(SupportTypeEnum eValue : SupportTypeEnum.values()){
			if(eValue.getValue()==value){
				return eValue;
			}
		}
		return null;
	}

}



