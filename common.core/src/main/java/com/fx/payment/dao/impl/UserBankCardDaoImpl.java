package com.fx.payment.dao.impl;

import java.util.List;

import com.fx.payment.dao.IUserBankCardDao;
import com.fx.payment.model.UserBankCard;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserBankCardDaoImpl extends BaseDao<UserBankCard> implements IUserBankCardDao {

    public UserBankCardDaoImpl() {
        super( IUserBankCardDao.class.getName());
    }

	@Override
	public List<UserBankCard> getBankCardByUid(int uid) {
		return findList("getByUid", uid);
	}
}