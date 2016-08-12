package com.fx.common.dao.impl;

import com.fx.common.dao.IActionLogDao;
import com.fx.common.model.ActionLog;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class ActionLogDaoImpl extends BaseDao<ActionLog> implements IActionLogDao {

    public ActionLogDaoImpl() {
        super(IActionLogDao.class.getName());
    }
}