package com.fx.logs.service;

import com.fx.logs.model.AccessLogs;
import mybatis.framework.core.service.IValueObjectService;

public interface IAccessLogsService extends IValueObjectService<AccessLogs> {
	
	public int saveAccessLogs(AccessLogs accessLogs);
	
}