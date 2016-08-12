package com.fx.global.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.global.dao.IUidGeneratorDao;
import com.fx.global.model.Group;
import com.fx.global.model.UidGenerator;
import com.fx.global.service.IUidGeneratorService;

import mybatis.framework.core.service.BaseVOService;

@Service
public class UidGeneratorServiceImpl extends BaseVOService<Group> implements IUidGeneratorService{
	
	@Autowired
	private IUidGeneratorDao uidGeneratorDao;

	@Override
	public int selectNextIdLive(int companyId) {
		return uidGeneratorDao.selectNextIdLive(companyId);
	}

	@Override
	public int selectNextIdDemo(int companyId) {
		return uidGeneratorDao.selectNextIdDemo(companyId);
	}

	@Override
	public void updateNextIdLive(int companyId,int nextId) {
		uidGeneratorDao.updateNextIdLive(companyId,nextId);
		
	}

	@Override
	public void updateNextIdDemo(int companyId,int nextId) {
		uidGeneratorDao.updateNextIdDemo(companyId,nextId);	
	}
	


}
