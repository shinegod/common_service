package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ITradeDictionaryDao;
import com.fx.bonus.model.TradeDictionary;

@Repository
public class TradeDictionaryDaoImpl extends BaseDao<TradeDictionary> implements ITradeDictionaryDao {

    public TradeDictionaryDaoImpl() {
        super(ITradeDictionaryDao.class.getName());
    }
}