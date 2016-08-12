package com.fx.global.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import com.fx.global.dao.IGroupDao;
import com.fx.global.model.Group;


@Repository
public class GroupDaoImpl extends BaseDao<Group>implements IGroupDao {
	
	
	public GroupDaoImpl() {
	        super(DBContants.DB_GLOBAL,IGroupDao.class.getName());
	    }


	@Override
	public Group findGroupByID(int id) {
		return (Group) super.findOne("findGroupById", id);
	}

	@Override
	public Group findGroupByName(String groupName) {
		return (Group) super.findOne("findGroupByName", groupName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getMT4DemoGroupList(int companyId) {
		return (List<Group>) super.selectList("selectDemoGroup",companyId);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getMT4LiveGroupList(int companyId) {
		return (List<Group>) super.selectList("selectLiveGroup",companyId);
	}


	@Override
	public List<Group> getMT4GroupList(int companyId) {
		return (List<Group>) super.selectList("selectGroup",companyId);
	}

	

}
