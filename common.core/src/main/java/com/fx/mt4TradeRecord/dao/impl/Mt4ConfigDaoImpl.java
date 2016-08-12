package com.fx.mt4TradeRecord.dao.impl;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import org.springframework.stereotype.Repository;

import com.fx.mt4TradeRecord.dao.IMt4ConfigDao;
import com.fx.mt4TradeRecord.model.Mt4Config;

@Repository
public class Mt4ConfigDaoImpl extends BaseDao<Mt4Config> implements IMt4ConfigDao {

    public Mt4ConfigDaoImpl() {
        super(DBContants.DB_MT4TRADE,IMt4ConfigDao.class.getName());
    }
}