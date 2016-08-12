package com.fx.payment.service.impl;

import java.util.List;

import com.fx.payment.dao.IUserBankCardDao;
import com.fx.payment.model.UserBankCard;
import com.fx.payment.service.IUserBankCardService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBankCardServiceImpl extends BaseVOService<UserBankCard> implements IUserBankCardService {
    @Autowired
    private IUserBankCardDao userBankCardDao;

	@Override
	public List<UserBankCard> getBankCardByUid(int uid) {
		return userBankCardDao.getBankCardByUid(uid);
	}
}