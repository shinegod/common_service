package com.fx.customer.service.impl;

import com.fx.customer.dao.ICustSourceDao;
import com.fx.customer.dao.impl.CustSourceDaoImpl;
import com.fx.customer.model.CustCategory;
import com.fx.customer.model.CustSource;
import com.fx.customer.service.ICustSourceService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustSourceServiceImpl extends BaseVOService<CustSource> implements ICustSourceService {
    @Autowired
    private ICustSourceDao custSourceDao;

    /*@Autowired
    private CustSourceDaoImpl custSourceDaoImpl;*/


    @Override
    public List<CustSource> CustSourceAll() {
        return this.custSourceDao.CustSourceAll();
    }

    @Override
    public void insertCustSource(String name) {
        this.custSourceDao.insertCustSourceSet(name);
    }

    @Override
    public void updateCustSource(int id, String name) {
        this.custSourceDao.updateCustSource(id, name);
    }

    @Override
    public void deleteCustSource(int id) {
        this.custSourceDao.deleteCustSource(id);
    }

}