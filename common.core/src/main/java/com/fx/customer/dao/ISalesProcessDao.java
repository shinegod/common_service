package com.fx.customer.dao;

import com.fx.customer.model.SalesProcess;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ISalesProcessDao extends IValueObjectDao<SalesProcess> {

    public List<SalesProcess> SalesProcessAll() ;

    public void  insertSalesProcessSet(String name);

    public void updateSalesProcess(int id,String name);

    public void deleteSalesProcess(int id);
}