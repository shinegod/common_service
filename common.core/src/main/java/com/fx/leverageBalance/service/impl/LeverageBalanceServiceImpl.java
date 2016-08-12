package com.fx.leverageBalance.service.impl;

import com.fx.leverageBalance.dao.ILeverageBalanceDao;
import com.fx.leverageBalance.model.LeverageBalance;
import com.fx.leverageBalance.service.ILeverageBalanceService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LeverageBalanceServiceImpl extends BaseVOService<LeverageBalance> implements ILeverageBalanceService {
    @Autowired
    private ILeverageBalanceDao leverageBalanceDao;

    @Override
    public LeverageBalance selectByLeverage(Integer lev) {
        return leverageBalanceDao.selectByLeverage(lev);
    }
}