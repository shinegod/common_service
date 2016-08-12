package com.fx.leverageBalance.dao.impl;

import com.fx.leverageBalance.dao.ILeverageBalanceDao;
import com.fx.leverageBalance.model.LeverageBalance;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LeverageBalanceDaoImpl extends BaseDao<LeverageBalance> implements ILeverageBalanceDao {

    public LeverageBalanceDaoImpl() {
        super(ILeverageBalanceDao.class.getName());
    }

    @Override
    public LeverageBalance selectByLeverage(Integer lev) {
        return (LeverageBalance)findOne("selectByLeverage",lev);
    }
}