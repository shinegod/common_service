package com.fx.global.service;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.global.model.Company;

public interface ICompanyService extends IValueObjectService<Company>{
	
	/**
	 * selectCompanyByID 由ID获取组信息
	 * @param int
	 * @return Company
	 * @exception 
	*/
	public Company findCompanyByID(int id);
	
	
	
}
