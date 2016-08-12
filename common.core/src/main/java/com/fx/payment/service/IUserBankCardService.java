package com.fx.payment.service;

import java.util.List;

import com.fx.payment.model.UserBankCard;

import mybatis.framework.core.service.IValueObjectService;

public interface IUserBankCardService extends IValueObjectService<UserBankCard> {
	
	/**
	 * getBankCardByUid	获取某人所有的银行卡信息
	 * @param uid	用户Uid
	 * @return List<UserBankCard>	返回查询到的银行卡信息，没有值时返回null
	 * @exception 
	*/
	public List<UserBankCard> getBankCardByUid(int uid);
}