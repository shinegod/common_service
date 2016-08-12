package com.fx.configuration.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.configuration.dao.IIbEaDeveloperConfDao;
import com.fx.configuration.model.IbEaDeveloperConf;

@Repository
public class IbEaDeveloperConfDaoImpl extends BaseDao<IbEaDeveloperConf> implements IIbEaDeveloperConfDao {

    public IbEaDeveloperConfDaoImpl() {
        super(IIbEaDeveloperConfDao.class.getName());
    }
}