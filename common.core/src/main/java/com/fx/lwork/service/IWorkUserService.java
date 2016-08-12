package com.fx.lwork.service;

import java.util.List;

import com.fx.lwork.model.WorkUser;

import mybatis.framework.core.service.IValueObjectService;

public interface IWorkUserService extends IValueObjectService<WorkUser> {
	
	
	public List<WorkUser> findWorkUser();
	
}