package com.fx.configuration.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.configuration.dao.IBonusCommissionConfDao;
import com.fx.configuration.model.BonusCommissionConf;

@Repository
public class BonusCommissionConfDaoImpl extends BaseDao<BonusCommissionConf> implements IBonusCommissionConfDao {

    public BonusCommissionConfDaoImpl() {
        super(IBonusCommissionConfDao.class.getName());
    }
}