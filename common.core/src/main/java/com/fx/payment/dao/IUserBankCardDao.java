package com.fx.payment.dao;

import java.util.List;

import com.fx.payment.model.UserBankCard;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IUserBankCardDao extends IValueObjectDao<UserBankCard> {

	public List<UserBankCard> getBankCardByUid(int uid);
}