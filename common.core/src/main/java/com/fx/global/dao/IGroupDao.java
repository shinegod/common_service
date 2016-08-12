package com.fx.global.dao;

import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.global.model.Group;

public interface IGroupDao extends IValueObjectDao<Group> {
	
	/**
	 * selectGroupByID 由ID获取组信息
	 * @param int
	 * @return Group
	 * @exception 
	*/
	public Group findGroupByID(int id);
	
	/**
	 * selectGroupByGroupName 由ID获取组信息
	 * @param String
	 * @return Group
	 * @exception 
	*/
	public Group findGroupByName(String groupName);

	public List<Group> getMT4DemoGroupList(int companyId);
	
	public List<Group> getMT4LiveGroupList(int companyId);

	public List<Group> getMT4GroupList(int companyId);
	

}