package com.fx.bonus.service;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.bonus.model.Bonus;

public interface IBonusService extends IValueObjectService<Bonus>{

	/**
	 * findByUid	根据名称查询分红员信息
	 * @param Uid
	 * @return Bonus
	 * @exception 
	*/
	public List<Bonus> findByUid(int uid);
	
	/**
	 * findByDate	根据名称查询分红员信息
	 * @param date
	 * @return Bonus
	 * @exception 
	*/
	public List<Bonus> findByDate(String date);
	

	/**
	 * bonusPaid 添加新分红记录
	 * @param bonus
	 * @return boolean
	 * @exception 
	*/
	public int bonusPaid(int id);

	public int createBonus(Bonus bonus);

	public PageIterator<Bonus> pageQueryUnpaidBonus(int pageNo, int pageSize);

	public PageIterator<Bonus> pageQueryByCondition(int pageNo, int pageSize, Map<String, Object> queryConditionMap);
	
	public List<Bonus> getThisMonthBonusByUid(int uid);
}
