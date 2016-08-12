package com.fx.customer.dao.impl;

import com.fx.customer.dao.ISalesProcessDao;
import com.fx.customer.model.SalesProcess;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SalesProcessDaoImpl extends BaseDao<SalesProcess> implements ISalesProcessDao {

    public SalesProcessDaoImpl() {
        super(ISalesProcessDao.class.getName());
    }

    public List<SalesProcess> SalesProcessAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        return findList("selectAll", params);
    }

    public void  insertSalesProcessSet(String name){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="1";
		int maxId = 0;
		if(findOne("selectSalesProcessmaxid", params)!=null){
			  maxId = (Integer)findOne("selectSalesProcessmaxid", params);
		}
        params.put("id",maxId+1);
        params.put("name",name);
        params.put("status", status);
        insert("insertSalesProcess",params);
    }

    public void updateSalesProcess(int id,String name){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="1";
        params.put("id",id);
        params.put("name",name);
        params.put("status", status);
        this.doUpdate("updateByPrimaryKey", params);
    }

    public void deleteSalesProcess(int id){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="2";
        params.put("id",id);
        params.put("status",status);
        update("updateSalesProcessById", params);
    }
}