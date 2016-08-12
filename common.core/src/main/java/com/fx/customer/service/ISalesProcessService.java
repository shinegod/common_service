package com.fx.customer.service;

import com.fx.customer.model.SalesProcess;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ISalesProcessService extends IValueObjectService<SalesProcess> {


    public List<SalesProcess> SalesProcessAll() ;
    public void insertSalesProcess(String name);

    public void updateSalesProcess(int id, String name) ;

    public void deleteSalesProcess(int id) ;
}