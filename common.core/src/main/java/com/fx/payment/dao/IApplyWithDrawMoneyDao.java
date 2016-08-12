package com.fx.payment.dao;

import java.util.List;
import java.util.Map;

import com.fx.payment.model.ApplyWithDrawMoney;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IApplyWithDrawMoneyDao extends IValueObjectDao<ApplyWithDrawMoney> {

	/**
	 * queryCountByCondition	分页查询时用的查询总数
	 * @param params
	 * @return int
	 * @exception 
	*/
	public int queryCountByCondition(Map<String, Object> params);

	/**
	 * queryByCondition	分页查询时用的查询某一页的数据
	 * @param params
	 * @return List<ApplyWithDrawMoney>
	 * @exception 
	*/
	public List<ApplyWithDrawMoney> queryByCondition(Map<String, Object> params);

	public List<ApplyWithDrawMoney> queryList(int statusq,String userq);

	public List<ApplyWithDrawMoney> selsectNopage(Map map);

	public List<ApplyWithDrawMoney> queryListMoreTable(int statusq,String userq);
}