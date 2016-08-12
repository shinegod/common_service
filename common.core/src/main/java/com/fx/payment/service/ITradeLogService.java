package com.fx.payment.service;

import com.fx.payment.model.TradeLog;

import mybatis.framework.core.service.IValueObjectService;

public interface ITradeLogService extends IValueObjectService<TradeLog> {
	public TradeLog getByTradeId(int tradeId);
}