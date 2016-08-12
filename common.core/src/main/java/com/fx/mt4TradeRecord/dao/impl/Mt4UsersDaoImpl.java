package com.fx.mt4TradeRecord.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fx.mt4TradeRecord.dao.IMt4UsersDao;
import com.fx.mt4TradeRecord.model.Mt4Users;

@Repository
public class Mt4UsersDaoImpl extends BaseDao<Mt4Users> implements IMt4UsersDao {

    public Mt4UsersDaoImpl() {
        super(DBContants.DB_MT4TRADE,IMt4UsersDao.class.getName());
    }

	@Override
	public Mt4Users getMt4UsersByMt4Account(int mt4Account) {
		return (Mt4Users) super.findOne("getMt4UsersByMt4Account", mt4Account);
	}

	@Override
	public List<Mt4Users>  getMt4UsersByMt4AccountAndBalanceLeverage(Map map) {
		return super.findList("getMt4UsersByMt4AccountAndBalanceLeverage", map);
	}

	@Override
	public List<Mt4Users> findByPagination(Map<String, Object> params) {
		return super.findList("selectByPage2", params);
	}

    @Override
	public int queryPageSize(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public Integer repeatMT4Account(int login) {
		return (Integer)findOne("repeatMT4Account",login);
	}

	@Override
	public List<Mt4Users> getMt4UsersByLogin(List<Integer> login) {
		HashMap<String, List<Integer>> params = new HashMap<>();
		if(login!=null && login.size()>0){
			params.put("loginList",login);
		}
		return super.findList("getMt4UsersByLogin", params);
	}


}