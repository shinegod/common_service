package com.fx.payment.service;

import com.fx.payment.exception.PayException;
import com.fx.payment.model.TradeDetail;

public interface IPayerService {
	/**
	 * prepareToSendAddMoneyRequest	入金准备操作:生成交易详情，返回向支付方发起请求的链接
	 * @return String	请求链接
	 * @exception 
	*/
	public String prepareToSendAddMoneyRequest(TradeDetail detail) throws PayException;
	
}
