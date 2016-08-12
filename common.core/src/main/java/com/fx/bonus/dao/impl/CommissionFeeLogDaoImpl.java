package com.fx.bonus.dao.impl;

import com.fx.bonus.dao.ICommissionFeeLogDao;
import com.fx.bonus.model.CommissionFeeLog;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class CommissionFeeLogDaoImpl extends BaseDao<CommissionFeeLog> implements ICommissionFeeLogDao {

    public CommissionFeeLogDaoImpl() {
        super(ICommissionFeeLogDao.class.getName());
    }
}