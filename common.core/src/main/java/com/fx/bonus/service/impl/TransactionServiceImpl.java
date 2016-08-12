package com.fx.bonus.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.ITransactionDao;
import com.fx.bonus.enums.BonusEnum;
import com.fx.bonus.model.Bonus;
import com.fx.bonus.model.IBCommission;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.model.Transaction;
import com.fx.bonus.service.ITransactionService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends BaseVOService<Transaction> implements ITransactionService {
	@Autowired
	private ITransactionDao transactionDao;

	@Override
	public List<Transaction> findByUid(int uid) {
		return transactionDao.findByUid(uid);
	}

	@Override
	public List<Transaction> findBySettle(int isSettled) {
		return transactionDao.findBySettle(isSettled);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledTransaction(Calendar c) {
		return transactionDao.findLastMonthUnsettledTransaction(c);
	}

	@Override
	public int UpdateTransaction(Transaction transaction) {
		return transactionDao.doUpdate("updateByPrimaryKey", transaction);

	}

	@Override
	public List<Bonus> calculateBonus(String date) {
		return transactionDao.findList("calculateBonus", date);
	}

	@Override
	public List<Transaction> findRecentSettledTransaction(HashMap<String, String> dateRange) {
		return transactionDao.findList("findRecentSettledTransaction", dateRange);
	}

	@Override
	public int createNewTransaction(Transaction tr) {
		return transactionDao.createNewTransaction(tr);
	}

	@Override
	public PageIterator<Transaction> pageQueryTransactionByCondition(int pageNo, int pageSize, Map<String, Object> queryConditionMap) {
		List<Transaction> transactionList;

		transactionList=transactionDao.pageQueryTransactionByCondition(queryConditionMap);
				

		int offset = (pageNo -1) * pageSize;
		int totalCount=transactionList.size();
		PageIterator<Transaction> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<Transaction> resList;

		if (offset+pageSize>totalCount)
			resList=new ArrayList<Transaction>(transactionList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<Transaction>(transactionList.subList(offset, offset+pageSize));
		page.setData(resList);
		page.setTotalCount(totalCount);
		
		return page;
	}

	@Override
	public PageIterator<Transaction> pageQueryRecentTransaction(int pageNo,
			int pageSize) {
		List<Transaction> transactionList;
	
		transactionList=transactionDao.findRecentTransaction();
			
		
		int offset = (pageNo -1) * pageSize;
		int totalCount=transactionList.size();
		PageIterator<Transaction> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<Transaction> resList;

		if (offset+pageSize>totalCount)
			resList=new ArrayList<Transaction>(transactionList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<Transaction>(transactionList.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}

	
	@Override
	public List<Transaction> findByIdList(HashMap tidList) {
		return transactionDao.findList("findByIdList", tidList);
	}

	@Override
	public List<IBCommission> calculateIbCommission(String date) {
		return transactionDao.findList("calculateIbCommission", date);
	}

	@Override
	public List<IBCommissionDetail> calculateIbCommissionDetail(String date) {
		return transactionDao.findList("calculateIbCommissionDetail", date);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledIBCommission(Calendar c) {
		return transactionDao.findLastMonthUnsettledIBCommission(c);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledCommomCommission(Calendar c) {
		return transactionDao.findLastMonthUnsettledCommomCommission(c);
	}

	@Override
	public List<Transaction> findRecentSettledCommomCommission(
			HashMap<String, String> dateRange) {
		return transactionDao.findList("findRecentSettledCommomCommission", dateRange);
	}

	@Override
	public List<Transaction> findRecentSettledIBCommission(
			HashMap<String, String> dateRange) {
		return transactionDao.findList("findRecentSettledIBCommission", dateRange);
	}






}