package com.fx.customer.service.impl;

import java.util.List;

import com.fx.customer.dao.ICustCategoryDao;
import com.fx.customer.dao.impl.CustCategoryDaoImpl;
import com.fx.customer.model.CustCategory;
import com.fx.customer.service.ICustCategoryService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustCategoryServiceImpl extends BaseVOService<CustCategory> implements ICustCategoryService {
    
	@Autowired
    private ICustCategoryDao custCategoryDao;
	
	/*@Autowired
	private CustCategoryDaoImpl custCategoryDaoImpl;*/
	
	public List<CustCategory> CustomerSetAll(){
		return this.custCategoryDao.CustomerSetAll();
	}

	@Override
	public void insertCustomerSet(String name) {
		this.custCategoryDao.insertCustomerSet(name);
	}

	@Override
	public void updateCustomerSet(int id,String name) {
		this.custCategoryDao.updateCustomerSet(id,name);
	}

	@Override
	public void deleteCustomerSet(int id) {
		this.custCategoryDao.deleteCustomerSet(id);
	}


}