package com.fx.payment.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fx.payment.model.TradeDetail;

import mybatis.framework.core.dao.IValueObjectDao;

public interface ITradeDetailDao extends IValueObjectDao<TradeDetail> {

	/**
	 * queryCountByCondition	分页查询使用查询总量
	 * @param params
	 * @return int
	 * @exception 
	*/
	public int queryCountByCondition(Map<String, Object> params);

	/**
	 * queryByCondition	分页查询使用，查询每页数量
	 * @param params
	 * @return List<TradeDetail>
	 * @exception 
	*/
	public List<TradeDetail> queryByCondition(Map<String, Object> params);

	public List<TradeDetail> selectDetail(int opertype , int payertyoe,int statusq ,String userq);

	public int selectDetailBySuccess(int mt4Account);

	public List<TradeDetail> selectDetailByType(Map map);

	public List<TradeDetail> findAllByMT4acconut(Integer account);

	public List<TradeDetail> findAllByMT4acconutReturnFee(Integer account);

	public TradeDetail findByMerOrderNum(String merOrderNum);

	public Double findDepositAmountByDate(Map<String, Object> params);

	public Double findWithDrawAmountByDate(Map<String, Object> params);
	public Double findFirstDepsoitAmount(Map<String, Object> params);
	public Date findFirstDepsoitDate(Map<String, Object> params);
	public List<Integer> findDepositMt4AccountByDate(Map<String, Object> params);
	public List<TradeDetail> findTradedByMT4acconut(Map<String, Object> params);
	public Double getTotalWithDrawAmount(Map<String, Object> params);
	public Double getTotalDeposit(Map<String, Object> params);
}