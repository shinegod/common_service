package com.fx.customer.service.impl;

import com.fx.customer.dao.ISalesProcessDao;
import com.fx.customer.dao.impl.SalesProcessDaoImpl;
import com.fx.customer.model.CustSource;
import com.fx.customer.model.SalesProcess;
import com.fx.customer.service.ISalesProcessService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesProcessServiceImpl extends BaseVOService<SalesProcess> implements ISalesProcessService {
    @Autowired
    private ISalesProcessDao salesProcessDao;
  /*  @Autowired
    private SalesProcessDaoImpl salesProcessDaoImpl;*/


    public List<SalesProcess> SalesProcessAll() {
        return this.salesProcessDao.SalesProcessAll();
    }

    public void insertSalesProcess(String name) {
        this.salesProcessDao.insertSalesProcessSet(name);
    }

    public void updateSalesProcess(int id, String name) {
        this.salesProcessDao.updateSalesProcess(id, name);
    }

    public void deleteSalesProcess(int id) {
        this.salesProcessDao.deleteSalesProcess(id);
    }
}