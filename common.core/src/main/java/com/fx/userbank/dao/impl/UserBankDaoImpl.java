package com.fx.userbank.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.userbank.dao.IUserBankDao;
import com.fx.userbank.model.UserBank;

@Repository
public class UserBankDaoImpl extends BaseDao<UserBank> implements IUserBankDao {

    public UserBankDaoImpl() {
        super(IUserBankDao.class.getName());
    }
}