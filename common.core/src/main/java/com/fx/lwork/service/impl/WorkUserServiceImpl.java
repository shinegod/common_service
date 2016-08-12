package com.fx.lwork.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.lwork.dao.IWorkUserDao;
import com.fx.lwork.model.WorkUser;
import com.fx.lwork.service.IWorkUserService;

@Service
public class WorkUserServiceImpl extends BaseVOService<WorkUser> implements IWorkUserService {
    
	@Autowired
    private IWorkUserDao workUserDao;

	@Override
	public List<WorkUser> findWorkUser() {
		 Map<String, Object> params = new HashMap<String, Object>();
		//TODO 查询所有数据为1的
		 params.put("status", "1");
		return  workUserDao.findList("selectAll", params);
	}

	public void doUpdateUsers(List<WorkUser> users) {
		
	}
}