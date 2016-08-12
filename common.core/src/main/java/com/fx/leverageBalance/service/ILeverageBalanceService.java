package com.fx.leverageBalance.service;

import com.fx.leverageBalance.model.LeverageBalance;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

public interface ILeverageBalanceService extends IValueObjectService<LeverageBalance> {

    public LeverageBalance selectByLeverage(Integer lev);

}