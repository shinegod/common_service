package com.fx.logs.service.impl;

import com.fx.logs.dao.ILogsDao;
import com.fx.logs.model.Logs;
import com.fx.logs.service.ILogsService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Michael on 8/24/2016.
 */
@Service
public class LogsServiceImpl extends BaseVOService<Logs> implements ILogsService {
    @Autowired
    ILogsDao logsDao;
}
