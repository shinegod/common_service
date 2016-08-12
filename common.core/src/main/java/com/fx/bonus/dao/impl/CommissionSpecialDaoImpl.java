package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ICommissionSpecialDao;
import com.fx.bonus.model.CommissionSpecial;

@Repository
public class CommissionSpecialDaoImpl extends BaseDao<CommissionSpecial> implements ICommissionSpecialDao {

    public CommissionSpecialDaoImpl() {
        super(ICommissionSpecialDao.class.getName());
    }
}