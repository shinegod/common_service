package com.fx.mt4TradeRecord.dao;

import com.fx.mt4TradeRecord.model.Mt4Users;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IMt4UsersDao extends IValueObjectDao<Mt4Users> {

	public Mt4Users getMt4UsersByMt4Account(int mt4Account);

	List<Mt4Users> findByPagination(Map<String, Object> params);

	int queryPageSize(Map<String, Object> params);

	public Integer repeatMT4Account(int login);

	public List<Mt4Users> getMt4UsersByLogin(List<Integer> login);

	public List<Mt4Users>  getMt4UsersByMt4AccountAndBalanceLeverage(Map map);

}