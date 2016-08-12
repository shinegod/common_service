package com.fx.mt4TradeRecord.dao;

import com.fx.RPQueryVolume.model.RPQueryVolume;
import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.mt4TradeRecord.model.Mt4Trades;
import com.fx.mt4TradeRecord.model.Mt4Volume;
import com.fx.util.Pagination;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMt4TradesDao extends IValueObjectDao<Mt4Trades> {

	public List<Mt4Trades> getMt4TradesByAccount(int login, Date date1,Date date2);
	public List<Mt4Trades> getMt4TradesByAccountAndDate(Map<String, Object> params);
	public List<Integer> findTradedLoginByDate(Map<String, Object> params);
	public List<Mt4Trades> queryByDateAndLogin(Map<String, Object> params);
	public List<Mt4Trades> queryByDateLogin(Map<String, Object> params);

	public int queryCountByCondition(Map<String, Object> params);

	public List<Mt4Trades> queryByCondition(Map<String, Object> params);

	public int queryCountByConditionByIb(Map<String, Object> params);

	public List<Mt4Trades> queryByConditionByIb(Map<String, Object> params);

	public int queryCountByConditionByAccount(Map<String, Object> params);

	public List<Mt4Trades> queryByConditionByAccount(Map<String, Object> params);

	public int queryCountByConditionByAccountHistory(Map<String, Object> params);

	public List<Mt4Trades> queryByConditionByAccountHistory(
			Map<String, Object> params);

	public int queryCountByConditionByIbHistory(Map<String, Object> params);

	public List<Mt4Trades> queryByConditionByIbHistory(
			Map<String, Object> params);

	public List<Mt4Trades> queryBymt4Type(int mt4Type ,String interviewDate1,String interviewDate2,String login,List loginlist);

	List<Mt4Trades> getMt4TradesByCloseTime(Date begin, Date end);

	List<Mt4Trades> findTradesByPage(Pagination pagination, Map<String, Object> params);

	public List<Mt4Trades> findCommissionDetail(int login);

	public int queryCountByConditionGroupBySymbol(Map<String, Object> params);

	public List<Mt4Trades> pageQueryByConditionGroupBySymbol(
			Map<String, Object> params);

	public int queryCountByConditionMt4AccountDetailBySymbo(
			Map<String, Object> params);

	public List<Mt4Trades> pageQueryByConditionMt4AccountDetailBySymbol(
			Map<String, Object> params);

	List<Mt4Trades> queryByTimeLogin(List<Integer> login, String interviewDate1, String interviewDate2);

	List<Mt4Trades> getByTimeAccount(List<Integer> login, String interviewDate1, String interviewDate2);

	public int countByPositions(Map params);

	public List<RPQueryVolume> queryVolumnList(Map map);

	public RPQueryVolume queryVolumnSum(Map map);

	public List<BbookuserReport> selectBbookProfit(Map map);

}