package com.fx.bonus.dao.impl;

import com.fx.bonus.dao.IIbcommissionPaymentLogDao;
import com.fx.bonus.model.IbcommissionPaymentLog;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class IbcommissionPaymentLogDaoImpl extends BaseDao<IbcommissionPaymentLog> implements IIbcommissionPaymentLogDao {

    public IbcommissionPaymentLogDaoImpl() {
        super(IIbcommissionPaymentLogDao.class.getName());
    }
}