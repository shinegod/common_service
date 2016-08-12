package com.fx.admin.service;

import java.util.List;

import com.fx.admin.model.PowerItem;

import mybatis.framework.core.service.IValueObjectService;

public interface IPowerItemService extends IValueObjectService<PowerItem> {
	
	/**
	 * addPowerItem	增加一条权限信息
	 * @param powerItem
	 * @return int
	 * @exception 
	*/
	public int addPowerItem(PowerItem powerItem);
	
	/**
	 * updatePowerItem	修改一条权限信息
	 * @param powerItem
	 * @return int
	 * @exception 
	*/
	public int updatePowerItem(PowerItem powerItem);
	
	/**
	 * deletePowerItem	删除一条权限信息
	 * @param powerItem
	 * @return int
	 * @exception 
	*/
	public int deletePowerItem(PowerItem powerItem);
	
	/**
	 * findAllPowerItem	获取所有的权限信息
	 * @return List<PowerItem>
	 * @exception 
	*/
	public List<PowerItem> findAllPowerItem();
	
	/**
	 * findById	根据ID查询权限信息
	 * @param id
	 * @return PowerItem
	 * @exception 
	*/
	public PowerItem findById(int id);
	
}