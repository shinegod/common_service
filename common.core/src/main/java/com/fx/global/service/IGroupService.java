package com.fx.global.service;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.global.model.Group;

public interface IGroupService extends IValueObjectService<Group>{
	
	/**
	 * selectGroupByID 由ID获取组信息
	 * @param int
	 * @return Group
	 * @exception 
	*/
	public Group findGroupByID(int id);
	
	/**
	 * selectGroupByGroupName 由ID获取组信息
	 * @param String groupName
	 * @return Group
	 * @exception 
	*/
	public Group findGroupByName(String groupName);

	/**
	 * getMT4DemoGroupList 获取所有Demo组
	 * @param int companyId
	 * @return List<Group>
	 * @exception 
	*/
	public List<Group> getMT4DemoGroupList(int companyId);
	
	/**
	 * getMT4DemoGroupList 获取所有Live组
	 * @param int companyId
	 * @return List<Group>
	 * @exception 
	*/
	public List<Group> getMT4LiveGroupList(int companyId);

	/**
	 * getMT4DemoGroupList 获取所有Live组
	 * @param int companyId
	 * @return HashMap<String,Group>
	 * @exception 
	*/
	public HashMap<String,Group> getMT4LiveGroupMap(int companyId);
	/**
	 * getGroupList 获取所有组
	 * @param null
	 * @return List<Group>
	 * @exception 
	*/
	public List<Group> getGroupList(int companyId);

}
