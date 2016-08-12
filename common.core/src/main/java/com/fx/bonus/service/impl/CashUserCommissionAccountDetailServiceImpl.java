package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.dao.ICashUserCommissionAccountDetailDao;
import com.fx.bonus.model.CashUserCommissionAccountDetail;
import com.fx.bonus.model.CashUserCommissionDetail;
import com.fx.bonus.model.IBCommissionAccountDetail;
import com.fx.bonus.service.ICashUserCommissionAccountDetailService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashUserCommissionAccountDetailServiceImpl extends BaseVOService<CashUserCommissionAccountDetail> implements ICashUserCommissionAccountDetailService {
    @Autowired
    private ICashUserCommissionAccountDetailDao cashUserCommissionAccountDetailDao;

	@Override
	public PageIterator<CashUserCommissionAccountDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionAccountDetailDao.queryCountByCondition(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionAccountDetail> iBCommissionList = cashUserCommissionAccountDetailDao.queryByCondition(dateRange);
		PageIterator<CashUserCommissionAccountDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public PageIterator<CashUserCommissionAccountDetail> pageQueryByConditionByUidDate(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionAccountDetailDao.queryCountByUidDate(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionAccountDetail> iBCommissionList = cashUserCommissionAccountDetailDao.queryByUidDate(dateRange);
		PageIterator<CashUserCommissionAccountDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}