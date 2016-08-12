package com.fx.crm.comm.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.crm.comm.model.CommissionMonth;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ICommissionMonthService extends IValueObjectService<CommissionMonth> {

	
	/**
	 * 佣金审核列表
	 * @param pagination
	 * @param queryFrom
	 * @param queryTo
     * @param status
	 * @return
	 */
	PageIterator<CommissionMonth> pageQueryByConditiont(Pagination pagination,
			String queryFrom, String queryTo, Integer status);

	List<CommissionMonth> getCommissionMonthByIbId(
			HashMap<String, Object> params);


	PageIterator<CommissionMonth> pageQueryByIbId(
			HashMap<String, Object> params, Pagination pagination);
	/**
	 * 根据时间与支付状态查询出佣金列表
	 * @param queryFrom
	 * @param queryTo
	 * @param status
	 * @return
	 */
	List<CommissionMonth> findCommissionByCondition(String queryFrom,
			String queryTo, Integer status);

	List<CommissionMonth> getCommissionMonthByUser(Pagination pagination,
			String interviewDate1, String interviewDate2, String login);

	List<CommissionMonth> getCommissionMonthByUserStatus(Pagination pagination,
			String interviewDate1, String interviewDate2, String login,
			String status, int uid);

	public List<CommissionMonth> findIBCommissionByParams(HashMap<String, Object> paramCommission);

	public BigDecimal getTotalNoReturnByUidAndMt4(CommissionMonth commissionMonth);

	public BigDecimal getTotalReturnByUidAndMt4(CommissionMonth commissionMonth);

	public List<CommissionMonth> findIBCommissionDetailsByParams(HashMap<String, Object> paramCommission);

	public BigDecimal getTotalByUidAndMt4(CommissionMonth commissionMonth);

	public BigDecimal getTotalVolumeByParams(HashMap<String, Object> paramCommission);

	public BigDecimal getTotalCommissionByParams(HashMap<String, Object> paramCommission);

	public List<CommissionMonth> getCommissionMonthByParams(HashMap<String, Object> paramCommission);

    public PageIterator<CommissionMonth> pageQueryByIserIdAndMT4Account(HashMap<String, Object> params, Pagination pagination);
}