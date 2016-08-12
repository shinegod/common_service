package com.fx.bonus.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.fx.bonus.enums.BonusEnum;
import com.fx.bonus.model.Transaction;
import mybatis.framework.core.dao.IValueObjectDao;

public interface ITransactionDao extends IValueObjectDao<Transaction> {
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
	public List<Transaction> findBySettle(int isSettled);
	
	
	/**
	 * findDuedUnsettledTransaction	返回到给定日子那天，1.入金足够或者超过一个的月或者2.出金超过1天的所有 转账
	 * @param date in String
	 * @return Transaction list
	 * @exception 
	*/	

	public List<Transaction> findLastMonthUnsettledTransaction(Calendar c);
	public List<Transaction> findLastMonthUnsettledIBCommission(Calendar c);
	public List<Transaction> findLastMonthUnsettledCommomCommission(Calendar c);

	public int UpdateAll(List<Transaction> updateTrs);

	public int createNewTransaction(Transaction tr);

	public List<Transaction> findRecentTransaction();

	public List<Transaction> pageQueryTransactionByCondition(Map<String, Object> queryConditionMap);
}