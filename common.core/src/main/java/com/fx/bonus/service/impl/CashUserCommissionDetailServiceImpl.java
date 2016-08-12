package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.dao.ICashUserCommissionDetailDao;
import com.fx.bonus.model.CashUserCommissionDetail;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.service.ICashUserCommissionDetailService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashUserCommissionDetailServiceImpl extends BaseVOService<CashUserCommissionDetail> implements ICashUserCommissionDetailService {
    @Autowired
    private ICashUserCommissionDetailDao cashUserCommissionDetailDao;

	@Override
	public List<CashUserCommissionDetail> findCashUserCommissionDetailByAccountUid(
			HashMap<String, Object> dateRange) {
		return cashUserCommissionDetailDao.findList("findCashUserCommissionDetailByAccountUid", dateRange);
	}

	@Override
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionDetailDao.queryCountByCondition(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionDetail> iBCommissionList = cashUserCommissionDetailDao.queryByCondition(dateRange);
		PageIterator<CashUserCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByAccount(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionDetailDao.queryCountByAccount(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionDetail> iBCommissionList = cashUserCommissionDetailDao.queryByAccount(dateRange);
		PageIterator<CashUserCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	
	@Override
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByUid(
			Integer userId) {
		return cashUserCommissionDetailDao.getCashUserCommissionDetailByUid(userId);
	}
	@Override
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByUid(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionDetailDao.queryCountByUid(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionDetail> iBCommissionList = cashUserCommissionDetailDao.queryByUid(dateRange);
		PageIterator<CashUserCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByAccount(
			int mt4Account) {
		return cashUserCommissionDetailDao.getCashUserCommissionDetailByAccount(mt4Account);
	}
	@Override
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByMt4Account(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionDetailDao.queryCountByMt4Account(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionDetail> iBCommissionList = cashUserCommissionDetailDao.queryByMt4Account(dateRange);
		PageIterator<CashUserCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByUidDate(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = cashUserCommissionDetailDao.queryCountByUidDate(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<CashUserCommissionDetail> iBCommissionList = cashUserCommissionDetailDao.queryByUidDate(dateRange);
		PageIterator<CashUserCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}