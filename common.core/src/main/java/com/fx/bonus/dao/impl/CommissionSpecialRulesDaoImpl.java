package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ICommissionSpecialRulesDao;
import com.fx.bonus.model.CommissionSpecialRules;

@Repository
public class CommissionSpecialRulesDaoImpl extends BaseDao<CommissionSpecialRules> implements ICommissionSpecialRulesDao {

    public CommissionSpecialRulesDaoImpl() {
        super(ICommissionSpecialRulesDao.class.getName());
    }
}