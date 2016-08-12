package com.fx.bonus.dao;

import java.sql.Date;
import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.bonus.model.Bonus;

public interface IBonusDao extends IValueObjectDao<Bonus>{
	
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

	public List<Bonus> getThisMonthBonusByUid(int uid);

}
