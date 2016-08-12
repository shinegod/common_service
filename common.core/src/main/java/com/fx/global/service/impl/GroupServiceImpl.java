package com.fx.global.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.global.dao.IGroupDao;
import com.fx.global.model.Group;
import com.fx.global.service.IGroupService;

import mybatis.framework.core.service.BaseVOService;

@Service
public class GroupServiceImpl extends BaseVOService<Group> implements IGroupService{
	
	@Autowired
	private IGroupDao groupDao;
	
	@Override
	public Group findGroupByID(int id) {
		return groupDao.findGroupByID(id);
	}

	@Override
	public Group findGroupByName(String groupName) {
		return groupDao.findGroupByName(groupName);
	}

	@Override
	public List<Group> getMT4DemoGroupList(int companyId) {
		return groupDao.getMT4DemoGroupList(companyId);
	}

	@Override
	public List<Group> getMT4LiveGroupList(int companyId) {
		return groupDao.getMT4LiveGroupList(companyId);
	}

	@Override
	public List<Group> getGroupList(int companyId) {
		return groupDao.getMT4GroupList(companyId);
	}

	@Override
	public HashMap<String, Group> getMT4LiveGroupMap(int companyId) {
		HashMap<String, Group> mt4LiveGroupMap=new HashMap<String, Group>();
		 List<Group> mT4LiveGroupList=getMT4LiveGroupList(companyId);
		for (int i=0;i<mT4LiveGroupList.size();i++){
			mt4LiveGroupMap.put(mT4LiveGroupList.get(i).getId()+"", mT4LiveGroupList.get(i));
		}
		return mt4LiveGroupMap;
	}

}
