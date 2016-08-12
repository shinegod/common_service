package com.fx.admin.dao;

import com.fx.admin.model.PowerItem;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IPowerItemDao extends IValueObjectDao<PowerItem> {

	/**
	 * deleteByUpdate	将权限状态修改为删除状态
	 * @param powerItem
	 * @return int
	 * @exception 
	*/
	public int deleteByUpdate(PowerItem powerItem);
}