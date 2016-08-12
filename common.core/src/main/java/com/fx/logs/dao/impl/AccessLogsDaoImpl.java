package com.fx.logs.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.logs.dao.IAccessLogsDao;
import com.fx.logs.model.AccessLogs;

@Repository
public class AccessLogsDaoImpl extends BaseDao<AccessLogs> implements IAccessLogsDao {

    public AccessLogsDaoImpl() {
        super(IAccessLogsDao.class.getName());
    }

	@Override
	public int saveAccessLogs(AccessLogs accessLogs) {
		return super.doInsert("insert", accessLogs);
	}
    
}