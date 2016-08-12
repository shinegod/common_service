package com.fx.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.customer.dao.ICustCategoryDao;
import com.fx.customer.model.CustCategory;
import com.fx.event.model.Event;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustCategoryDaoImpl extends BaseDao<CustCategory> implements ICustCategoryDao  {
	
	public CustCategoryDaoImpl() {
        super(ICustCategoryDao.class.getName());
    }

	public List<CustCategory> CustomerSetAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return findList("selectAll", params);
	}

	public void  insertCustomerSet(String name){
		Map<String, Object> params = new HashMap<String, Object>();
		String status ="1";
		int maxId = 0;
		if(findOne("selectcustcategorymaxid", params)!=null){
			maxId = (Integer)findOne("selectcustcategorymaxid", params);
		}
		params.put("id",maxId+1);
		params.put("name",name);
		params.put("status", status);
		CustCategory cust = new CustCategory();
		cust.setCateName(name);
		cust.setStatus(status);
		//this.doInsert("insert",cust);
		insert("insertCustomerSetCust",params);
	}

	public void updateCustomerSet(int id,String name){
		Map<String, Object> params = new HashMap<String, Object>();
		String status ="1";
		params.put("id",id);
		params.put("name",name);
		params.put("status", status);
		this.doUpdate("updateByPrimaryKey", params);
	}

	public void deleteCustomerSet(int id){
		Map<String, Object> params = new HashMap<String, Object>();
        String status ="2";
        params.put("id",id);
        params.put("status",status);
		update("updateCustomerSetById", params);
	}

}