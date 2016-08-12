package com.fx.global.dao;

import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.global.model.Company;
import com.fx.global.model.Group;

public interface ICompanyDao extends IValueObjectDao<Company> {
	
	/**
	 * selectCompanyByID 由ID获取组信息
	 * @param int
	 * @return Company
	 * @exception 
	*/
	public Company findCompanyByID(int id);
	

	//public Company findGroupByName(String groupName);

	//public List<Company> getMT4DemoGroupList();
	

	
	
	
}