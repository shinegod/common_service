package com.fx.common.service.impl;

import com.fx.common.dao.IActionLogDao;
import com.fx.common.model.ActionLog;
import com.fx.common.service.IActionLogService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionLogServiceImpl extends BaseVOService<ActionLog> implements IActionLogService {
    @Autowired
    private IActionLogDao actionLogDao;
}