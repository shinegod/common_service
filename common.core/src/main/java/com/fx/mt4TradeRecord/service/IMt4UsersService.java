package com.fx.mt4TradeRecord.service;

import com.fx.MT4.vo.MT4User;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMt4UsersService extends IValueObjectService<Mt4Users> {
	public Mt4Users getMt4UsersByMt4Account(int mt4Account, Integer dataSourceId);

	public Pagination<Mt4Users> findByPagination(Pagination pagination, Map<String, Object> params,String dataRole, int dataSource);

	public int queryPageSize(Map<String, Object> params, Integer dataSourceId);

	public Integer repeatMT4Account(int login);

	public List<Mt4Users> getMt4UsersByLogin(List<Integer> login, Integer dataSourceId);
	
	/**
	 * 查询某时间段内某类账号下新开的mt4 包括合计
	 * @param paramTrade
	 * @param dataSource
	 * @return
	 */
	public List<Mt4Users> queryNewAccounts(HashMap<String, Object> paramTrade,
			int dataSource);

	public List<Mt4Users> queryAllAccounts(HashMap<String, Object> paramTrade,
										   int dataSource);
	public List<Integer> findLoginByDate(HashMap<String, Object> paramTrade,
										   int dataSource);

	//-------合计余额
	public Double getTotalBalance(HashMap<String, Object> paramTrade,
			int dataSource);
	//------合计浮动盈亏
	public Double getTotalFloatingProfitLoss(
			HashMap<String, Object> paramTrade, int dataSource);
	//------合计净值
	public Double getTotalEquity(HashMap<String, Object> paramTrade,
			int dataSource);

	public Mt4Users findByEmail(Mt4Users mt4User, int dataSource);

}