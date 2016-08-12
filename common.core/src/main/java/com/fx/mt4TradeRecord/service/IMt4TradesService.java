package com.fx.mt4TradeRecord.service;

import com.fx.RPQueryVolume.model.RPQueryVolume;
import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;
import com.fx.mt4TradeRecord.model.Mt4Trades;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.*;

public interface IMt4TradesService extends IValueObjectService<Mt4Trades> {
	
	public List<Mt4Trades> getMt4TradesByAccount(int login, Date date1, Date date2, Integer dataSourceId);
	public List<Mt4Trades> getMt4TradesByAccountAndDate(Map<String, Object> params, Integer dataSourceId);
	public List<Integer> findTradedLoginByDate(Map<String, Object> params, Integer dataSourceId);
	public List<Mt4Trades> queryByDateAndLogin(Map<String, Object> params, Integer dataSourceId);
	public List<Mt4Trades> queryByDateLogin(Map<String, Object> params, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditiont(int pageNo, int pageSize, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditiontByIb(ArrayList<Integer> mt4AccountList, Date date1, Date date2, int pageNo, int pageSize, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditiontByAccount(Integer mt4Account, int pageNo, int pageSize, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditiontByAccountHistory(int mt4Account, Date date1, Date date2, int pageNo, int pageSize, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditiontByIbHistory(ArrayList<Integer> mt4IdList, int pageNo, int pageSize, Integer dataSourceId);
	public List<Mt4Trades> queryBymt4Type(int mt4Type, String interviewDate1, String interviewDate2, String login, Integer dataSourceId,List loginlist);
	List<Mt4Trades> getMt4TradesByCloseTime(Date begin, Date end, Integer dataSourceId);
	List<Mt4Trades> findTradesByPage(Pagination pagination, Map<String, Object> params, int dataSourceId);
	public List<Mt4Trades> findCommissionDetail(int login, Integer dataSourceId);
	public List<Mt4Trades> findMt4TradesByLoginList(List<UserMT4Account> userMT4AccountList_uk, String queryFrom, String queryTo, int dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditionGroupBySymbol(int mt4Account, String queryFrom, String queryTo, Pagination pagination, Integer dataSourceId);
	public PageIterator<Mt4Trades> pageQueryByConditionMt4AccountDetailBySymbol(Integer mt4Account, String symbol, String queryFrom, String queryTo, Pagination pagination, Integer dataSourceId);
	public List<Mt4Trades> getMt4TradesByList(List<Integer> list3, Integer id);
	/**
	 * 按照login,cmd查找已完成交易的所有记录
	 * @param mt4Account
	 * @param cmdList
	 * @param dataSourceId
	 * @param startDate
	 *@param endDate @return
	 */
	public List<Mt4Trades> findMt4TradesByLoginAndCmdInList(int mt4Account,
															List<Integer> cmdList, Integer dataSourceId, String startDate, String endDate);
	/**
	 * 按照login,日期查找已完成交易的入金
	 * @param loginList
	 * @param today
	 * @return
	 */
	public List<ComprehensiveReportOneday> getAmountInByAccountTime(List<Integer> loginList, Date today);
	/**
	 * 按照login,日期查找已完成交易的出金
	 * @param loginList
	 * @param today
	 * @return
	 */
	public List<ComprehensiveReportOneday> getAmountOutByAccountTime(
            List<Integer> loginList, Date today);
	/**
	 * 按照login,日期查找已完成交易的佣金和利息
	 * @param loginList
	 * @param today
	 * @return
	 */
	public List<ComprehensiveReportOneday> getCommissionAndTaxesByAccountTime(
            List<Integer> loginList, Date today);
	/**
	 * 按照login,日期查找已完成交易的盈亏
	 * @param loginList
	 * @param today
	 * @return
	 */
	public List<ComprehensiveReportOneday> getProfitByAccountTime(
            List<Integer> loginList, Date today);
	public List<ComprehensiveReportOneday> getAmountInByAccountTimeToday(
            List<Integer> loginList, Date today, Date date2);
	public List<ComprehensiveReportOneday> getAmountOutByAccountTimeToday(
            List<Integer> loginList, Date today, Date date2);
	public List<ComprehensiveReportOneday> getCommissionAndTaxesByAccountTimeToday(
            List<Integer> loginList, Date today, Date date2);
	public List<ComprehensiveReportOneday> getProfitByAccountTimeToday(
            List<Integer> loginList, Date today, Date date2);
	/**
	 * 按照login,日期查找已完成交易的存取款，调整
	 * @param interviewDate1
	 * @param interviewDate2
	 * @param login
	 * @return
	 */
	List<Mt4Trades> queryByTimeLogin(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId);

	/**
	 * 查询某段时间内某些账号的所有交易历史
	 * @param paramTrade
	 * @param dataSource
	 * @return
	 */
	public List<Mt4Trades> queryTradeshistory(
            HashMap<String, Object> paramTrade, int dataSource);
	public Integer getTotalVolume(HashMap<String, Object> paramTrade,
                                  int dataSource);
	public Double getTotalCommission(HashMap<String, Object> paramTrade,
                                     int dataSource);
	public Double getTotalSwaps(HashMap<String, Object> paramTrade,
                                int dataSource);
	public Double getTotalProfit(HashMap<String, Object> paramTrade,
                                 int dataSource);

	
	/**
	 * 根据mt4账号进行分组
	 * @param date1
	 * @param date2
	 * @param id
	 * @return
	 */
	public List<Mt4Trades> getMt4TradesByCloseTimeGroup(Date date1, Date date2,
                                                        Integer id);

	public List<Mt4Trades> getmt4TradesByTrading(Map<String, Object> params);
	public List<Mt4Trades> getMt4TradesByAccountGroup(
            HashMap<String, Object> params);

	/**
	 * 查询某段时间内某些账号的所有持仓记录以及合计
	 * @param paramTrade
	 * @param dataSource
	 * @return
	 */
	public List<Mt4Trades> queryPositionInquiry(
            HashMap<String, Object> paramTrade, int dataSource);
	public Integer getPositionTotalVolume(HashMap<String, Object> paramTrade,
                                          int dataSource);
	public Double getPositionTotalCommission(
            HashMap<String, Object> paramTrade, int dataSource);
	public Double getPositionTotalSwaps(HashMap<String, Object> paramTrade,
                                        int dataSource);
	public Double getPositionTotalProfit(HashMap<String, Object> paramTrade,
                                         int dataSource);

	public List<Mt4Trades> getmt4TradesByAgent(HashMap<String, Object> params);

	

	/**
	 * 汇总账号所有的入金金额
	 * @param mt4Account
	 * @param dataSourceId
	 * @param startDate
	 *@param endDate @return
	 */
	public Double getDepositTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate);
	/**
	 * 汇总账号所有的出金金额
	 * @param mt4Account
	 * @param dataSourceId
	 * @param startDate
	 *@param endDate @return
	 */
	public Double getWithdrawalTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate);
	public Double getClosedPLTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate);

	/**
	 * 按照login,日期查找已完成交易的止损止盈
	 * @param interviewDate1
	 * @param interviewDate2
	 * @param login
	 * @return
	 */
	List<Mt4Trades> getByTimeAccount(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId);

	public int countByPositions(Map params);

	public List<RPQueryVolume> queryVolumnList(Map map);

	public List<BbookuserReport> selectBbookProfit(Map map);

	public RPQueryVolume queryVolumnSum(Map map);

    public Integer getVolumeByMT4Accounts(List<Integer> mt4accountlist, Date date1, Date date2);

    public Integer getSumVolumeByMT4AccountAndSymbol(List<Integer> mt4accountlist, List<String> tradingGroupSymbolsList, Date date1, Date date2);

	public Double getFloatingPLTotalByLogin(int mt4Account, int dataSourceId, String s, String s1);
}