package com.fx.bonus.service;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.EaCommission;
import com.fx.bonus.model.EaCommissionAccountDetail;
import com.fx.bonus.model.EaCommissionDetail;
import com.fx.bonus.model.IBCommissionDetail;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IEaCommissionDetailService extends IValueObjectService<EaCommissionDetail> {
	public List<EaCommissionDetail> findEaCommissionDetail(HashMap<String, Object> dateRange);
	public int createEaCommissionDetail(EaCommissionDetail eaCommissionDetail);
	public List<EaCommissionDetail> findEaCommissionDetailByAccount(HashMap<String, Object> dateRange);
	public List<EaCommissionDetail> findEaCommissionDetailByEaid(HashMap<String, Object> dateRange);
	public PageIterator<EaCommissionDetail> pageQueryByConditionByAccountEaid(
			Integer mt4Account, int eaId, int pageNo, int pageSize);
	public PageIterator<EaCommissionDetail> pageQueryByConditionByEaId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
	public PageIterator<EaCommissionDetail> pageQueryByConditionByAccount(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
	public List<EaCommissionDetail> getEaCommissionDetailByEaidDate(
			HashMap<String, Object> dateIbId);
	public List<EaCommissionDetail> getIBCommissionDetailByAccountDate(
			HashMap<String, Object> dateIbId);
}