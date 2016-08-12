package com.fx.crm.comm.service;

import com.fx.crm.comm.model.CommissionBase;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.text.ParseException;

public interface ICommissionBaseService extends IValueObjectService<CommissionBase> {
	
	/**
	 * 获取返佣详情
	 * @param params
	 * @return
	 */
	public List<CommissionBase> getCommissionBaseByIbId(HashMap<String, Object> params);
	
	/**
	 * 获取某个账号的交易记录
	 * @param params
	 * @return
	 */
	public List<CommissionBase> getCommissionBaseByAccount(
			HashMap<String, Object> params);


	public PageIterator<CommissionBase> pageQueryByAccount(
			HashMap<String, Object> params, Pagination pagination);

	
	/**
	 * 查询返佣详情并依据交易组进行分组
	 * @param pagination
	 * @param params
	 * @return
	 */
	public PageIterator<CommissionBase> pageQueryByConditiontAndGroupByTradeCateId(
			Pagination pagination, Map<String, Object> params);

	public PageIterator<CommissionBase> pageQueryByConditiont(
			Pagination pagination, Map<String, Object> params);

	/**
	 * 查询出返佣账号的佣金汇总。
	 * @param pagination
	 * @param queryFrom
	 * @param queryTo
	 * @return
	 */
	public PageIterator<CommissionBase> pageQueryGroupByAgentAccount(
			Pagination pagination, String queryFrom, String queryTo);

	public List<CommissionBase> getCommissionBaseByTradingGroup(
			Map<String, Object> params);

	public List<CommissionBase> getCommissionBaseByAccountGroup(
			HashMap<String, Object> params);

	public List<CommissionBase> getCommissionBaseByAgentAccount(
			HashMap<String, Object> params);

	public List<CommissionBase> getCommissionBaseByTradingAccount(
			HashMap<String, Object> params);

	public List<CommissionBase> getCommissionBaseByAgentUser(
			HashMap<String, Object> params);

	public List<CommissionBase> getCommissionBasedDetailByTradingAccount(
			HashMap<String, Object> params);

	List<CommissionBase> getCommissionBaseByTradingGroupTrader(HashMap<String, Object> params);

	List<CommissionBase> getCommissionBaseByAgentAccountTrader(HashMap<String, Object> params);

    public List<CommissionBase> getCommissionBaseByIBIdAndAgentAccount(HashMap<String, Object> params);

    public List<CommissionBase> getCommissionBaseByIBIdAndMT4Account(HashMap<String, Object> params);

    /**
     * 参数为交易账号
     * @param params
     * @return
     */
    public List<CommissionBase> getCommissionBaseByMT4Account(HashMap<String, Object> params);

public List<CommissionBase> getCommissionBaseByLogin(int ibIds,List<Integer> userIdList,
			int ib_ids, String interviewDate1, String interviewDate2,
			Integer dataSourceId, String login) throws ParseException;
	public List<CommissionBase> getCommissionBaseInnerByTradingGroup(
			String interviewDate1, String interviewDate2, Integer dataSourceId,int ibIds) throws ParseException;

	public List<CommissionBase> getCommissionBasedDetailByTradingGroup(
			String interviewDate1, String interviewDate2, int ibIds,
			int ib_ids, String login, Integer dataSourceId) throws ParseException ;
	public List<CommissionBase> getCommissionBaseByinnerIbId (
			String interviewDate1,String interviewDate2,HashMap<String, Object> params) throws ParseException;
	List<CommissionBase> getCommissionBasedDetailByTradingAccountByMT4Account(HashMap<String, Object> params);
}