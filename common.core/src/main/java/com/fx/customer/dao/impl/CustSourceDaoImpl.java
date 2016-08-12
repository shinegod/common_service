package com.fx.customer.dao.impl;

import com.fx.customer.dao.ICustSourceDao;
import com.fx.customer.model.CustCategory;
import com.fx.customer.model.CustSource;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustSourceDaoImpl extends BaseDao<CustSource> implements ICustSourceDao {

    public CustSourceDaoImpl() {
        super(ICustSourceDao.class.getName());
    }

    public List<CustSource> CustSourceAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        return findList("selectAll", params);
    }

    public void  insertCustSourceSet(String name){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="1";
		int maxId = 0;
		if(findOne("selectCustSourcemaxid", params)!=null){
			  maxId = (Integer)findOne("selectCustSourcemaxid", params);
		}
        params.put("id",maxId+1);
        params.put("name",name);
        params.put("status", status);
        insert("insertCustSourceCust",params);
    }

    public void updateCustSource(int id,String name){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="1";
        params.put("id",id);
        params.put("name",name);
        params.put("status", status);
        this.doUpdate("updateByPrimaryKey", params);
    }

    public void deleteCustSource(int id){
        Map<String, Object> params = new HashMap<String, Object>();
        String status ="2";
        params.put("id",id);
        params.put("status",status);
        update("updateCustSourceById", params);
    }
}