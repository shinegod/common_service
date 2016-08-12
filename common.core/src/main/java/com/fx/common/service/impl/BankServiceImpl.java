package com.fx.common.service.impl;

import com.fx.common.dao.IBankDao;
import com.fx.common.model.Bank;
import com.fx.common.service.IBankService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends BaseVOService<Bank> implements IBankService {
    @Autowired
    private IBankDao bankDao;
}