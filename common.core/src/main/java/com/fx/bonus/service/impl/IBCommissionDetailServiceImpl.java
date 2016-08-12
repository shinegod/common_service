package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.bonus.dao.IIBCommissionDetailDao;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.service.IIBCommissionDetailService;

@Service
public class IBCommissionDetailServiceImpl extends BaseVOService<IBCommissionDetail> implements IIBCommissionDetailService {
    @Autowired
    private IIBCommissionDetailDao iBCommissionDetailDao;

	@Override
	public int createIBCommissionDetail(IBCommissionDetail ibCommissionDetail) {
		return iBCommissionDetailDao.doInsert("insert", ibCommissionDetail);
	}

	@Override
	public List<IBCommissionDetail> findIBCommissionDetail(
			HashMap<String, Object> dateRange) {
		return iBCommissionDetailDao.findList("findIBCommissionDetail", dateRange);
	}

	@Override
	public List<IBCommissionDetail> findGuaranIBCommissionDetail(
			HashMap<String, Object> dateRange) {
		return iBCommissionDetailDao.findList("findGuaranIBCommissionDetail", dateRange);
	}

	@Override
	public PageIterator<IBCommissionDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = iBCommissionDetailDao.queryCountByCondition(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<IBCommissionDetail> iBCommissionList = iBCommissionDetailDao.queryByCondition(dateRange);
		PageIterator<IBCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public List<IBCommissionDetail> findIBCommissionDetailAccount(
			HashMap<String, Object> dateRange) {
		return iBCommissionDetailDao.findList("findIBCommissionDetailAccount", dateRange);
	}

	@Override
	public List<IBCommissionDetail> findGuaranIBCommissionDetailUid(
			HashMap<String, Object> dateRange) {
		return iBCommissionDetailDao.findList("findGuaranIBCommissionDetailUid", dateRange);
	}

	@Override
	public PageIterator<IBCommissionDetail> pageQueryByConditionByAccount(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = iBCommissionDetailDao.queryCountByAccount(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<IBCommissionDetail> iBCommissionList = iBCommissionDetailDao.queryByAccount(dateRange);
		PageIterator<IBCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public List<IBCommissionDetail> getIBCommissionDetailByUid(int uid,int ib_id) {
		return iBCommissionDetailDao.getIBCommissionDetailByUid(uid,ib_id);
	}

	@Override
	public PageIterator<IBCommissionDetail> pageQueryByConditionByUid(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = iBCommissionDetailDao.queryCountByUid(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<IBCommissionDetail> iBCommissionList = iBCommissionDetailDao.queryByUid(dateRange);
		PageIterator<IBCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public PageIterator<IBCommissionDetail> pageQueryByConditionByIbIdDate(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = iBCommissionDetailDao.queryCountByIbIdDate(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<IBCommissionDetail> iBCommissionList = iBCommissionDetailDao.queryByIbIdDate(dateRange);
		PageIterator<IBCommissionDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public List<IBCommissionDetail> getIBCommissionDetailByUidDate(
			HashMap<String, Object> dateIbId) {
		return iBCommissionDetailDao.findList("getIBCommissionDetailByUidDate", dateIbId);
	}
}