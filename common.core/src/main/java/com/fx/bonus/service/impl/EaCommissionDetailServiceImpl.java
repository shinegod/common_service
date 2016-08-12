package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.IEaCommissionDetailDao;
import com.fx.bonus.model.EaCommissionDetail;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.service.IEaCommissionDetailService;
import com.fx.payment.model.UserMT4Account;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaCommissionDetailServiceImpl extends BaseVOService<EaCommissionDetail> implements IEaCommissionDetailService {
    @Autowired
    private IEaCommissionDetailDao eaCommissionDetailDao;

	@Override
	public List<EaCommissionDetail> findEaCommissionDetail(
			HashMap<String, Object> dateRange) {
		return eaCommissionDetailDao.findList("findEaCommissionDetail", dateRange);
	}

	@Override
	public int createEaCommissionDetail(EaCommissionDetail eaCommissionDetail) {
		return eaCommissionDetailDao.doInsert("insert", eaCommissionDetail);
	}

	@Override
	public List<EaCommissionDetail> findEaCommissionDetailByAccount(
			HashMap<String, Object> dateRange) {
		return eaCommissionDetailDao.findList("findEaCommissionDetailByAccount", dateRange);
	}
	@Override
	public List<EaCommissionDetail> findEaCommissionDetailByEaid(
			HashMap<String, Object> dateRange) {
		return eaCommissionDetailDao.findList("findEaCommissionDetailByEaid", dateRange);
	}
	@Override
	public PageIterator<EaCommissionDetail> pageQueryByConditionByAccountEaid(
			Integer mt4Account, int eaId, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eaId", eaId);
		params.put("mt4Account", mt4Account);
		int totalCount = eaCommissionDetailDao.queryCountByConditionByAccountEaid(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<EaCommissionDetail> userList = eaCommissionDetailDao.queryByConditionByAccountEaid(params);
		PageIterator<EaCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<EaCommissionDetail> pageQueryByConditionByEaId(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = eaCommissionDetailDao.queryCountByCondition(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<EaCommissionDetail> iBCommissionList = eaCommissionDetailDao.queryByCondition(dateRange);
		PageIterator<EaCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public PageIterator<EaCommissionDetail> pageQueryByConditionByAccount(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = eaCommissionDetailDao.queryCountByAccount(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<EaCommissionDetail> iBCommissionList = eaCommissionDetailDao.queryByAccount(dateRange);
		PageIterator<EaCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public List<EaCommissionDetail> getEaCommissionDetailByEaidDate(
			HashMap<String, Object> dateIbId) {
		return eaCommissionDetailDao.findList("getEaCommissionDetailByEaidDate", dateIbId);
	}
	@Override
	public List<EaCommissionDetail> getIBCommissionDetailByAccountDate(
			HashMap<String, Object> dateIbId) {
		return eaCommissionDetailDao.findList("getIBCommissionDetailByAccountDate", dateIbId);
	}
}