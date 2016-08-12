package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ITradeCurrencyDictionaryDao;
import com.fx.bonus.model.TradeCurrencyDictionary;

@Repository
public class TradeCurrencyDictionaryDaoImpl extends BaseDao<TradeCurrencyDictionary> implements ITradeCurrencyDictionaryDao {

    public TradeCurrencyDictionaryDaoImpl() {
        super(ITradeCurrencyDictionaryDao.class.getName());
    }
}