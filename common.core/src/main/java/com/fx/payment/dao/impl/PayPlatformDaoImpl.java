package com.fx.payment.dao.impl;

import com.fx.payment.dao.IPayPlatformDao;
import com.fx.payment.model.PayPlatform;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class PayPlatformDaoImpl extends BaseDao<PayPlatform> implements IPayPlatformDao {

    public PayPlatformDaoImpl() {
        super( IPayPlatformDao.class.getName());
    }
}