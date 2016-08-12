package com.fx.bonus.service;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.CashUserCommissionDetail;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ICashUserCommissionDetailService extends IValueObjectService<CashUserCommissionDetail> {
	/**
	 * 查询出一段时间内的某个Account或者某个用户的交易记录
	 * @param dateRange  查询参数的集合
	 * @return
	 */
	public List<CashUserCommissionDetail> findCashUserCommissionDetailByAccountUid(HashMap<String, Object> dateRange);

	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);

	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByAccount(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
			
	/**
	 * 查询某个用户的现金股利账号的交易记录
	 * @param userId
	 * @return
	 */
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByUid(
			Integer userId);
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByUid(
			HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByAccount(
			int mt4Account);
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByMt4Account(
			HashMap<String, Object> dateRange, int pageNo, int pageSize);
	public PageIterator<CashUserCommissionDetail> pageQueryByConditionByUidDate(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
}