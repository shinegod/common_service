package com.fx.payment.dao.impl;

import com.fx.payment.dao.ITradeLogDao;
import com.fx.payment.model.TradeLog;
import com.fx.user.model.User;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class TradeLogDaoImpl extends BaseDao<TradeLog> implements ITradeLogDao {

    public TradeLogDaoImpl() {
        super( ITradeLogDao.class.getName());
    }
	@Override
	public TradeLog getByTradeId(int tradeId) {
		return (TradeLog) super.findOne("getByTradeId", tradeId);
	}
}