package com.fx.bonus.service;

import java.util.HashMap;
import java.util.List;
import com.fx.bonus.model.CashUserCommission;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ICashUserCommissionService extends IValueObjectService<CashUserCommission> {

	PageIterator<CashUserCommission> pageQueryByConditionByIbId(
			int status, String queryFrom, String queryTo, int pageNo,
			int pageSize);
	PageIterator<CashUserCommission> pageQueryByUid(Integer id, int pageNo,
													int pageSize);
	List<CashUserCommission> getCashUserCommissionByUidDate(
			HashMap<String, Object> dateIbId);
}