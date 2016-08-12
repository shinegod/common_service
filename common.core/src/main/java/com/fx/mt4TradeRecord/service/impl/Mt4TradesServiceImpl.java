
package com.fx.mt4TradeRecord.service.impl;
import com.fx.RPQueryVolume.model.RPQueryVolume;
import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.util.DateUtil;
import com.github.pagehelper.PageHelper;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.mt4TradeRecord.dao.IMt4TradesDao;
import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;
import com.fx.mt4TradeRecord.model.Mt4Trades;
import com.fx.mt4TradeRecord.service.IMt4TradesService;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.Pagination;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Mt4TradesServiceImpl extends BaseVOService<Mt4Trades> implements IMt4TradesService {

    @Autowired
    private IMt4TradesDao mt4TradesDao;

	@Autowired
	private IMt4UsersService mt4UsersService;

	@Override
	public List<Mt4Trades> getMt4TradesByAccount(int login, Date date1, Date date2, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date1", date1);
		params.put("date2", date2);
		params.put("login", login);
		return mt4TradesDao.getMt4TradesByAccount(login, date1, date2);
//		return null;
	}

	@Override
	public List<Mt4Trades> getMt4TradesByAccountAndDate(Map<String, Object> params, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.getMt4TradesByAccountAndDate(params);
	}
	@Override
	public List<Integer> findTradedLoginByDate(Map<String, Object> params, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findTradedLoginByDate(params);
	}
	@Override
	public List<Mt4Trades> queryByDateAndLogin(Map<String, Object> params, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.queryByDateAndLogin(params);
	}

	@Override
	public List<Mt4Trades> queryByDateLogin(Map<String, Object> params, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.queryByDateLogin(params);
	}
	@Override
	public PageIterator<Mt4Trades> pageQueryByConditiont(int pageNo, int pageSize, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		int totalCount = mt4TradesDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.queryByCondition(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditiontByIb(ArrayList<Integer> mt4AccountList, Date date1,Date date2, int pageNo, int pageSize, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", mt4AccountList);
		params.put("date1", date1);
		params.put("date2", date2);
		int totalCount = mt4TradesDao.queryCountByConditionByIb(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.queryByConditionByIb(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditiontByAccount(
			Integer mt4Account, int pageNo, int pageSize, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mt4Account", mt4Account);
		int totalCount = mt4TradesDao.queryCountByConditionByAccount(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.queryByConditionByAccount(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditiontByAccountHistory(int mt4Account,
			Date date1, Date date2, int pageNo, int pageSize, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mt4Account", mt4Account);
		params.put("date1", date1);
		params.put("date2", date2);
		int totalCount = mt4TradesDao.queryCountByConditionByAccountHistory(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.queryByConditionByAccountHistory(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
		
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditiontByIbHistory(
			ArrayList<Integer> mt4IdList, int pageNo, int pageSize, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", mt4IdList);
		int totalCount = mt4TradesDao.queryCountByConditionByIbHistory(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.queryByConditionByIbHistory(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public List<Mt4Trades> queryBymt4Type(int mt4Type ,String interviewDate1,String interviewDate2,String login, Integer dataSourceId,List loginlist) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.queryBymt4Type(mt4Type , interviewDate1, interviewDate2, login,loginlist);
	}

	@Override
	public List<Mt4Trades> getMt4TradesByCloseTime(Date begin, Date end, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.getMt4TradesByCloseTime(begin, end);
	}

	@Override
	public List<Mt4Trades> findTradesByPage(Pagination pagination, Map<String, Object> params, int dataSourceId) {
        multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findTradesByPage(pagination, params);
	}

	@Override
	public List<Mt4Trades> findCommissionDetail(int login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findCommissionDetail(login);
	}

	@Override
	public List<Mt4Trades> findMt4TradesByLoginList(
			List<UserMT4Account> userMT4AccountList_uk, String queryFrom,
			String queryTo, int dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		List<Integer> userMT4AccountList = new ArrayList<Integer>();
		for (UserMT4Account userMT4Account : userMT4AccountList_uk) {
			userMT4AccountList.add(userMT4Account.getMt4Account());
		}
		params.put("userMT4AccountList", userMT4AccountList);
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		return mt4TradesDao.findList("findMt4TradesByLoginList", params);
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditionGroupBySymbol(
			int mt4Account, String queryFrom, String queryTo, Pagination pagination, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mt4Account", mt4Account);
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		int totalCount = mt4TradesDao.queryCountByConditionGroupBySymbol(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.pageQueryByConditionGroupBySymbol(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<Mt4Trades> pageQueryByConditionMt4AccountDetailBySymbol(
			Integer mt4Account, String symbol, String queryFrom,
			String queryTo, Pagination pagination, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mt4Account", mt4Account);
		params.put("symbol", symbol);
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		int totalCount = mt4TradesDao.queryCountByConditionMt4AccountDetailBySymbo(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Mt4Trades> userList = mt4TradesDao.pageQueryByConditionMt4AccountDetailBySymbol(params);
		PageIterator<Mt4Trades> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public List<Mt4Trades> getMt4TradesByList(List<Integer> list3, Integer id) {
		multiDataSourceSet(id);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("uidList", list3);
		return mt4TradesDao.findList("getMt4TradesByList", params);
	}

	@Override
	public List<Mt4Trades> findMt4TradesByLoginAndCmdInList(int mt4Account,
															List<Integer> cmdList, Integer dataSourceId, String startDate, String endDate) {
		multiDataSourceSet(dataSourceId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("cmdList", cmdList);
		params.put("mt4Account", mt4Account);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		return mt4TradesDao.findList("findMt4TradesByLoginAndCmdInList", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getAmountInByAccountTime(List<Integer> loginList, Date today) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		return mt4TradesDao.findList("getAmountInByAccountTime", params);
	}
	@Override
	public List<Mt4Trades> queryTradeshistory(
			HashMap<String, Object> paramTrade, int dataSource) {
		multiDataSourceSet(dataSource);
		return mt4TradesDao.findList("queryTradeshistory", paramTrade);
	}

	@Override
	public List<ComprehensiveReportOneday> getAmountOutByAccountTime(
			List<Integer> loginList, Date today) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		return mt4TradesDao.findList("getAmountOutByAccountTime", params);
	}
	@Override
	public Integer getTotalVolume(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Integer) mt4TradesDao.findOne("getTotalVolume", paramTrade);
	}

	@Override
	public List<ComprehensiveReportOneday> getCommissionAndTaxesByAccountTime(
			List<Integer> loginList, Date today) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		return mt4TradesDao.findList("getCommissionAndTaxesByAccountTime", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getProfitByAccountTime(
			List<Integer> loginList, Date today) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		return mt4TradesDao.findList("getProfitByAccountTime", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getAmountInByAccountTimeToday(
			List<Integer> loginList, Date today, Date date2) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		params.put("date2",date2);
		return mt4TradesDao.findList("getAmountInByAccountTimeToday", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getAmountOutByAccountTimeToday(
			List<Integer> loginList, Date today, Date date2) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		params.put("date2",date2);
		return mt4TradesDao.findList("getAmountOutByAccountTimeToday", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getCommissionAndTaxesByAccountTimeToday(
			List<Integer> loginList, Date today, Date date2) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		params.put("date2",date2);
		return mt4TradesDao.findList("getCommissionAndTaxesByAccountTimeToday", params);
	}

	@Override
	public List<ComprehensiveReportOneday> getProfitByAccountTimeToday(
			List<Integer> loginList, Date today, Date date2) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("loginList", loginList);
		params.put("today", today);
		params.put("date2",date2);
		return mt4TradesDao.findList("getProfitByAccountTimeToday", params);
	}

	@Override
	public List<Mt4Trades> queryByTimeLogin(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.queryByTimeLogin(login, interviewDate1, interviewDate2);
	 }
	@Override
	public Double getTotalCommission(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getTotalCommission", paramTrade);
	}

	@Override
	public Double getTotalSwaps(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getTotalSwaps", paramTrade);
	}

	@Override
	public Double getTotalProfit(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getTotalProfit", paramTrade);
	}

	@Override
	public List<Mt4Trades> getMt4TradesByCloseTimeGroup(Date date1, Date date2,
			Integer id) {
		multiDataSourceSet(id);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("date1", date1);
		params.put("date2", date2);
		return mt4TradesDao.findList("getMt4TradesByCloseTimeGroup", params);
	}


	@Override
	public List<Mt4Trades> getmt4TradesByTrading(Map<String, Object> params) {
		Integer dataSourceId = (Integer) params.get("dataSourceId");
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findList("getmt4TradesByTrading", params);
	}

	@Override
	public List<Mt4Trades> queryPositionInquiry(
			HashMap<String, Object> paramTrade, int dataSource) {
		multiDataSourceSet(dataSource);
		return mt4TradesDao.findList("queryPositionInquiry", paramTrade);
	}

	@Override
	public List<Mt4Trades> getMt4TradesByAccountGroup(
			HashMap<String, Object> params) {
		Integer dataSourceId = (Integer) params.get("dataSourceId");
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findList("getMt4TradesByAccountGroup", params);
	}

	@Override
	public Integer getPositionTotalVolume(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Integer) mt4TradesDao.findOne("getPositionTotalVolume", paramTrade);
	}

	@Override
	public Double getPositionTotalCommission(
			HashMap<String, Object> paramTrade, int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getPositionTotalCommission", paramTrade);
	}

	@Override
	public Double getPositionTotalSwaps(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getPositionTotalSwaps", paramTrade);
	}

	@Override
	public Double getPositionTotalProfit(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4TradesDao.findOne("getPositionTotalProfit", paramTrade);
	}
	@Override
	public Double getDepositTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate) {
		multiDataSourceSet(dataSourceId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("mt4Account", mt4Account);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		return (Double) mt4TradesDao.findOne("getDepositTotalByLogin", params);
	}

	@Override
	public List<Mt4Trades> getmt4TradesByAgent(HashMap<String, Object> params) {
		Integer dataSourceId = (Integer)params.get("dataSourceId");
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.findList("getmt4TradesByAgent", params);
	}

	@Override
	public Double getWithdrawalTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate) {
		multiDataSourceSet(dataSourceId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("mt4Account", mt4Account);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		return (Double) mt4TradesDao.findOne("getWithdrawalTotalByLogin", params);
	}
	@Override
	public List<Mt4Trades> getByTimeAccount(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4TradesDao.getByTimeAccount(login, interviewDate1, interviewDate2);
	}

	@Override
	public int countByPositions(Map params) {
		return mt4TradesDao.countByPositions(params);
	}

	@Override
	public List<RPQueryVolume> queryVolumnList(Map map) {
		return mt4TradesDao.queryVolumnList(map);
	}


	@Override
	public Double getClosedPLTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate) {
		multiDataSourceSet(dataSourceId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("mt4Account", mt4Account);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		return (Double) mt4TradesDao.findOne("getClosedPLTotalByLogin", params);
	}

	@Override
	public List<BbookuserReport> selectBbookProfit(Map map) {
		return mt4TradesDao.selectBbookProfit(map);
	}

	@Override
	public RPQueryVolume queryVolumnSum(Map map) {
		return mt4TradesDao.queryVolumnSum(map);
	}

    @Override
    public Integer getVolumeByMT4Accounts(List<Integer> mt4accountlist, Date date1, Date date2) {
        Map<String, Object> params = new HashMap<>();
        params.put("mt4accountlist", mt4accountlist);
        params.put("date1", date1);
        params.put("date2", date2);
        return (Integer) mt4TradesDao.findOne("getVolumeByMT4Accounts", params);
    }

    @Override
    public Integer getSumVolumeByMT4AccountAndSymbol(List<Integer> mt4accountlist, List<String> tradingGroupSymbolsList, Date date1, Date date2) {
        Map<String, Object> params = new HashMap<>();
        params.put("mt4accountlist", mt4accountlist);
        params.put("tradingGroupSymbolsList", tradingGroupSymbolsList);
        params.put("date1", date1);
        params.put("date2", date2);
        return (Integer) mt4TradesDao.findOne("getSumVolumeByMT4AccountAndSymbol", params);
    }

	@Override
	public Double getFloatingPLTotalByLogin(int mt4Account, int dataSourceId, String startDate, String endDate) {
		multiDataSourceSet(dataSourceId);
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("mt4Account", mt4Account);
		params.put("startDate",startDate);
		params.put("endDate",endDate);
		return (Double) mt4TradesDao.findOne("getFloatingPLTotalByLogin", params);
	}

}