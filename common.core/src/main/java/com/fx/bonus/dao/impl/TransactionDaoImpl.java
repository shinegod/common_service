package com.fx.bonus.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ITransactionDao;
import com.fx.bonus.enums.BonusEnum;
import com.fx.bonus.model.Transaction;
import com.fx.bonus.util.BonusUtil;

@Repository
public class TransactionDaoImpl extends BaseDao<Transaction> implements ITransactionDao {

    public TransactionDaoImpl() {
        super(ITransactionDao.class.getName());
    }

	@Override
	public List<Transaction> findByUid(int uid) {
		return super.findList("findByUid", uid);
	}
	
	@Override
	public List<Transaction> findBySettle(int isSettled) {
		return super.findList("findBySettle",isSettled);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledTransaction(Calendar today){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		
		String 	tdds=sf.format(today.getTime());
		return super.findList("findUnsettledTransaction", tdds);
		
	}

	@Override
	public int UpdateAll(List<Transaction> updateTrs) {
		return super.update("updateAll", updateTrs);
		
	}

	@Override
	public int createNewTransaction(Transaction tr) {
		return super.doInsert("insert", tr);
	}

	@Override
	public List<Transaction> pageQueryTransactionByCondition(Map<String, Object> queryConditionMap){
		int uid=(Integer)queryConditionMap.get("queryuid");
		int status=(Integer)queryConditionMap.get("querystatus");
		
		if (uid==0 && status==-1)
			return super.findList("findByStatus", 2);
		else if(uid==0)
			return super.findList("findByStatus", status);
		else if(status==-1)
			return super.findList("findByUid", uid);
		else 
			return super.findList("findByUidandStatus", queryConditionMap);
			
		
	}

	@Override
	public List<Transaction> findRecentTransaction() {
		
		return super.findList("findRecentTransaction", null);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledIBCommission(Calendar c) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		
		String 	tdds=sf.format(c.getTime());
		return super.findList("findUnsettledIBCommission", tdds);
	}

	@Override
	public List<Transaction> findLastMonthUnsettledCommomCommission(Calendar c) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		
		String 	tdds=sf.format(c.getTime());
		return super.findList("findUnsettledCommomCommission", tdds);
	}
	
	
}