package com.fx.task.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.task.dao.ITaskManageDao;
import com.fx.task.model.TaskManage;

@Repository
public class TaskManageDaoImpl extends BaseDao<TaskManage> implements ITaskManageDao {

    public TaskManageDaoImpl() {
        super(ITaskManageDao.class.getName());
    }

}