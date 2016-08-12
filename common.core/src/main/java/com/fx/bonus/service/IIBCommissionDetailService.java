package com.fx.bonus.service;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.bonus.model.IBCommissionDetail;

public interface IIBCommissionDetailService extends IValueObjectService<IBCommissionDetail> {
	public int createIBCommissionDetail(IBCommissionDetail ibCommissionDetail);
	public List<IBCommissionDetail> findIBCommissionDetail(HashMap<String, Object> dateRange);
	public List<IBCommissionDetail> findGuaranIBCommissionDetail(HashMap<String, Object> dateRange);
	public PageIterator<IBCommissionDetail> pageQueryByConditionByIbId(HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public List<IBCommissionDetail> findIBCommissionDetailAccount(HashMap<String, Object> dateRange);
	public List<IBCommissionDetail> findGuaranIBCommissionDetailUid(HashMap<String, Object> dateRange);
	public PageIterator<IBCommissionDetail> pageQueryByConditionByAccount(HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public List<IBCommissionDetail> getIBCommissionDetailByUid(int uid, int ib_id);
	public PageIterator<IBCommissionDetail> pageQueryByConditionByUid(HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public PageIterator<IBCommissionDetail> pageQueryByConditionByIbIdDate(HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public List<IBCommissionDetail> getIBCommissionDetailByUidDate(
			HashMap<String, Object> dateIbId);
}