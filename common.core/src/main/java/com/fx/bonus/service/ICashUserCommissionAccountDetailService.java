package com.fx.bonus.service;

import java.util.HashMap;

import com.fx.bonus.model.CashUserCommissionAccountDetail;
import com.fx.bonus.model.CashUserCommissionDetail;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ICashUserCommissionAccountDetailService extends IValueObjectService<CashUserCommissionAccountDetail> {

	PageIterator<CashUserCommissionAccountDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
	PageIterator<CashUserCommissionAccountDetail> pageQueryByConditionByUidDate(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
}