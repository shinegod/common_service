package com.fx.global.service;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.global.model.Group;
import com.fx.global.model.UidGenerator;

public interface IUidGeneratorService extends IValueObjectService<Group>{
	
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
