package com.fx.customer.dao;

import com.fx.customer.model.CustSource;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICustSourceDao extends IValueObjectDao<CustSource> {

    public List<CustSource> CustSourceAll() ;

    public void  insertCustSourceSet(String name);

    public void updateCustSource(int id,String name);

    public void deleteCustSource(int id);
}