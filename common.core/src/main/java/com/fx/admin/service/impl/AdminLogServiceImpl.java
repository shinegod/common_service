package com.fx.admin.service.impl;

import com.fx.admin.dao.IAdminLogDao;
import com.fx.admin.model.AdminLog;
import com.fx.admin.service.IAdminLogService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLogServiceImpl extends BaseVOService<AdminLog> implements IAdminLogService {
    @Autowired
    private IAdminLogDao adminLogDao;
}