package com.fx.logs.dao;

import com.fx.logs.model.AccessLogs;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IAccessLogsDao extends IValueObjectDao<AccessLogs> {
	
	public int saveAccessLogs(AccessLogs accessLogs);
	
}