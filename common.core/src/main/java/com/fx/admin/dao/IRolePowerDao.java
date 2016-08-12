package com.fx.admin.dao;

import java.util.List;
import java.util.Map;

import com.fx.admin.model.Role;
import com.fx.admin.model.RolePower;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IRolePowerDao extends IValueObjectDao<RolePower> {
	/**
	 * deleteByRoleId	根据角色ID删除所有的权限
	 * @param roleId void
	 * @exception 
	*/
	public void deleteByRoleId(int roleId);
	
	/**
	 * addRolePowerList	(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param rolePowerList
	 * @return int
	 * @exception 
	*/
	public int addRolePowerList(List<RolePower> rolePowerList);

	List<RolePower> findByRoleId(Integer roleId);
}