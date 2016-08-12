package com.fx.payment.service.impl;

import com.fx.payment.dao.IPayPlatformDao;
import com.fx.payment.model.PayPlatform;
import com.fx.payment.service.IPayPlatformService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayPlatformServiceImpl extends BaseVOService<PayPlatform> implements IPayPlatformService {
    @Autowired
    private IPayPlatformDao payPlatformDao;
}