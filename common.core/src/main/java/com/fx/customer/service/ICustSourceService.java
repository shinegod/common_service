package com.fx.customer.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.customer.dao.impl.CustCategoryDaoImpl;
import com.fx.customer.model.CustCategory;
import com.fx.customer.model.CustSource;

import mybatis.framework.core.service.IValueObjectService;

@Service
public interface ICustSourceService extends IValueObjectService<CustSource> {

    public List<CustSource> CustSourceAll();

    public void insertCustSource(String name);

    public void updateCustSource(int id , String name);

    public void deleteCustSource(int id);

}