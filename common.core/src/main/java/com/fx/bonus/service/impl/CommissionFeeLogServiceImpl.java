package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ICommissionFeeLogDao;
import com.fx.bonus.model.CommissionFeeLog;
import com.fx.bonus.service.ICommissionFeeLogService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommissionFeeLogServiceImpl extends BaseVOService<CommissionFeeLog> implements ICommissionFeeLogService {
    @Autowired
    private ICommissionFeeLogDao commissionFeeLogDao;

    @Override
    public List<CommissionFeeLog> findByCondition(HashMap<String, Object> paramTrade) {
        return commissionFeeLogDao.findList("findByCondition",paramTrade);
    }
}