package com.fx.lwork.dao.impl;

import com.fx.lwork.dao.IWorkUserDao;
import com.fx.lwork.model.WorkUser;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class WorkUserDaoImpl extends BaseDao<WorkUser> implements IWorkUserDao {

    public WorkUserDaoImpl() {
        super(IWorkUserDao.class.getName());
    }
}