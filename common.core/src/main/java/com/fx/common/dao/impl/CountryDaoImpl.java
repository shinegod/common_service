package com.fx.common.dao.impl;

import com.fx.common.dao.ICountryDao;
import com.fx.common.model.Country;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl extends BaseDao<Country> implements ICountryDao {

    public CountryDaoImpl() {
        super(ICountryDao.class.getName());
    }
}