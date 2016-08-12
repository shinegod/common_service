package com.fx.configuration.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.configuration.dao.IEaCommissionConfDao;
import com.fx.configuration.model.EaCommissionConf;

@Repository
public class EaCommissionConfDaoImpl extends BaseDao<EaCommissionConf> implements IEaCommissionConfDao {

    public EaCommissionConfDaoImpl() {
        super(IEaCommissionConfDao.class.getName());
    }
}