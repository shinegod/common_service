package com.fx.tradeAdjust.dao.impl;

import com.fx.tradeAdjust.dao.ITradeAdjustDao;
import com.fx.tradeAdjust.model.TradeAdjust;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class TradeAdjustDaoImpl extends BaseDao<TradeAdjust> implements ITradeAdjustDao {

    public TradeAdjustDaoImpl() {
        super(ITradeAdjustDao.class.getName());
    }
}