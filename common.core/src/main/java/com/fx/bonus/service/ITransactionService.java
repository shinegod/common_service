package com.fx.bonus.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.bonus.model.Bonus;
import com.fx.bonus.model.IBCommission;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.model.Transaction;

public interface ITransactionService extends IValueObjectService<Transaction> {
	/**
	 * findByUid	根据名称查询分红员信息
	 * @param Uid
	 * @return Transaction
	 * @exception 
	*/
	public List<Transaction> findByUid(int uid);
	
	/**
	 * findByUid	根据名称查询分红员信息
	 * @param isSettled from BonusEnum.Settled/BonusEnum.unSettled
	 * @return Transaction
	 * @exception 
	*/	
	public List<Transaction> findBySettle(int unsettled);
	


	public List<Transaction> findLastMonthUnsettledTransaction(Calendar c);
	
	public List<Transaction> findLastMonthUnsettledIBCommission(Calendar c);
	
	public List<Transaction> findLastMonthUnsettledCommomCommission(Calendar c);

	public int UpdateTransaction(Transaction transaction);

	public List<Bonus> calculateBonus(String date);
	
	public List<IBCommission> calculateIbCommission(String date);
	
	public List<IBCommissionDetail> calculateIbCommissionDetail(String date);
	

	public List<Transaction> findRecentSettledTransaction(HashMap<String, String> dateRange);
	
	public List<Transaction> findRecentSettledCommomCommission(HashMap<String, String> dateRange);
	
	public List<Transaction> findRecentSettledIBCommission(HashMap<String, String> dateRange);
	
	public int createNewTransaction(Transaction tr);

	public PageIterator<Transaction> pageQueryTransactionByCondition(int pageNo, int pageSize, Map<String, Object> queryConditionMap);

	public PageIterator<Transaction> pageQueryRecentTransaction(int pageNo,
																int pageSize);

	public List<Transaction> findByIdList(HashMap map);
	
}