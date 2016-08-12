package com.fx.task.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.task.dao.ITaskManageDao;
import com.fx.task.model.TaskManage;
import com.fx.task.service.ITaskManageService;
import com.fx.util.Constants;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManageServiceImpl extends BaseVOService<TaskManage> implements ITaskManageService {
    @Autowired
    private ITaskManageDao taskManageDao;

	@Override
	public List<TaskManage> queryListByCondition(TaskManage params) {
		params.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u1"));
		return taskManageDao.findList("queryListByCondition", params);
	}

	@Override
	public int queryCountByCondition(TaskManage params) {
		params.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u1"));
		return (int) taskManageDao.findOne("queryCountByCondition", params);
	}

}