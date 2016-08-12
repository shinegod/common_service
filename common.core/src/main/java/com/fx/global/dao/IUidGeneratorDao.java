package com.fx.global.dao;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.global.model.UidGenerator;

public interface IUidGeneratorDao extends IValueObjectDao<UidGenerator> {
	
	/**
	 * selectNextIdLive 获取下一个Live Account ID
	 * @param void
	 * @return int
	 * @exception 
	*/
	public int selectNextIdLive(int companyId);
	/**
	 * selectNextIdLive 获取下一个Demo Account ID
	 * @param null
	 * @return int
	 * @exception 
	*/
	public int selectNextIdDemo(int companyId);

	/**
	 * updaetNextIdLive	更新下一个Live Account ID
	 * @param int
	 * @return null
	 * @exception 
	*/
	public void updateNextIdLive(int companyId,int nextId);
	/**
	 * updaetNextIdLive	更新下一个Demo Account ID
	 * @param int
	 * @return null
	 * @exception 
	*/
	public void updateNextIdDemo(int companyId,int nextId);
	
}