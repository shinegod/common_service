package com.fx.leverageBalance.dao;

import com.fx.leverageBalance.model.LeverageBalance;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface ILeverageBalanceDao extends IValueObjectDao<LeverageBalance> {

    public LeverageBalance selectByLeverage(Integer lev);
}