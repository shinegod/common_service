package com.fx.user.dao.impl;

import com.fx.user.dao.ISalesOperateLogDao;
import com.fx.user.model.SalesOperateLog;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class SalesOperateLogDaoImpl extends BaseDao<SalesOperateLog> implements ISalesOperateLogDao {

    public SalesOperateLogDaoImpl() {
        super(ISalesOperateLogDao.class.getName());
    }
}