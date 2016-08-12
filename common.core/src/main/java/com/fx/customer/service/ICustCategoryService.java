package com.fx.customer.service;

import java.util.List;

import com.fx.customer.model.CustCategory;

import mybatis.framework.core.service.IValueObjectService;

public interface ICustCategoryService extends IValueObjectService<CustCategory> {
	
	public List<CustCategory> CustomerSetAll();

	public void insertCustomerSet(String name);

	public void updateCustomerSet(int id , String name);

	public void deleteCustomerSet(int id);

}