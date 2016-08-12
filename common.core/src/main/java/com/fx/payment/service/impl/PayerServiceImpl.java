package com.fx.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.fx.payment.exception.PayException;
import com.fx.payment.model.TradeDetail;
import com.fx.payment.service.IPayerService;
import com.fx.payment.service.ITradeDetailService;

public abstract class PayerServiceImpl implements IPayerService {
	
	@Autowired
	protected ITradeDetailService tradeDetailService;

	@Override
	public String prepareToSendAddMoneyRequest(TradeDetail tradeDetail) throws PayException {
		TradeDetail detail = tradeDetailService.findById(tradeDetail.getId());
		if(null == detail){
			return null;
		}
		String payUrl = getAddMoneyPayerUrl(detail);
		return payUrl;
	}
	
	protected abstract String getAddMoneyPayerUrl(TradeDetail tradeDetail);

}
