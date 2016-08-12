package com.fx.common.dao.impl;

import com.fx.common.dao.IBankDao;
import com.fx.common.model.Bank;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class BankDaoImpl extends BaseDao<Bank> implements IBankDao {

    public BankDaoImpl() {
        super(IBankDao.class.getName());
    }
}