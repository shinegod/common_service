package com.fx.common.dao.impl;

import com.fx.common.dao.IIndustryDao;
import com.fx.common.model.Industry;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class IndustryDaoImpl extends BaseDao<Industry> implements IIndustryDao {

    public IndustryDaoImpl() {
        super(IIndustryDao.class.getName());
    }
}