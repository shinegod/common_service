package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IProfitCommissionDao;
import com.fx.bonus.model.ProfitCommission;

@Repository
public class ProfitCommissionDaoImpl extends BaseDao<ProfitCommission> implements IProfitCommissionDao {

    public ProfitCommissionDaoImpl() {
        super(IProfitCommissionDao.class.getName());
    }
}