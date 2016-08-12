package com.fx.payment.service.impl;

import com.fx.payment.dao.ITradeLogDao;
import com.fx.payment.model.TradeLog;
import com.fx.payment.service.ITradeLogService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeLogServiceImpl extends BaseVOService<TradeLog> implements ITradeLogService {
    @Autowired
    private ITradeLogDao tradeLogDao;
	@Override
	public TradeLog getByTradeId(int tradeId) {
		return tradeLogDao.getByTradeId(tradeId);
	}
}