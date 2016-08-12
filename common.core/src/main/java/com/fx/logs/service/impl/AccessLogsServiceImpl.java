package com.fx.logs.service.impl;

import com.fx.logs.dao.IAccessLogsDao;
import com.fx.logs.model.AccessLogs;
import com.fx.logs.service.IAccessLogsService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogsServiceImpl extends BaseVOService<AccessLogs> implements IAccessLogsService {
  
	@Autowired
    private IAccessLogsDao accessLogsDao;

	@Override
	public int saveAccessLogs(AccessLogs accessLogs) {
		return accessLogsDao.saveAccessLogs(accessLogs);
	}
    
	
}