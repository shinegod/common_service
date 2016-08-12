package com.fx.trademsg.service;

import java.util.Map;

import com.fx.trademsg.model.CashDeposit;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ICashDepositService extends IValueObjectService<CashDeposit> {

	PageIterator<CashDeposit> pageQueryByCondition(int pageNo, int pageSize,
			Map<String, Object> params);
}