package com.fx.payment.dao;

import com.fx.payment.model.TradeLog;

import mybatis.framework.core.dao.IValueObjectDao;

public interface ITradeLogDao extends IValueObjectDao<TradeLog> {
	public TradeLog getByTradeId(int tradeId);
}