package com.fx.task.service;

import java.util.HashMap;
import java.util.List;

import com.fx.payment.model.UserMT4Account;
import com.fx.task.model.TaskManage;

import mybatis.framework.core.service.IValueObjectService;

public interface ITaskManageService extends IValueObjectService<TaskManage> {
	
	/**
	 * 根据查询条件（状态，处理人，申请账号）查询所有任务列表
	 * @param taskManage
	 * @return
	 */
	public List<TaskManage> queryListByCondition(TaskManage taskManage);

	public int queryCountByCondition(TaskManage taskManage);

}