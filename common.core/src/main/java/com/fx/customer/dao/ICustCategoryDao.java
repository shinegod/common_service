package com.fx.customer.dao;

import com.fx.customer.model.CustCategory;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICustCategoryDao extends IValueObjectDao<CustCategory> {
	//public void doinsert(CustCategory cust);
    public List<CustCategory> CustomerSetAll();

    public void  insertCustomerSet(String name);

    public void updateCustomerSet(int id,String name);

    public void deleteCustomerSet(int id);
}