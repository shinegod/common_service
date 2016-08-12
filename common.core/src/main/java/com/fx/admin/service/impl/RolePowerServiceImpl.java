package com.fx.admin.service.impl;

import com.fx.admin.dao.IRolePowerDao;
import com.fx.admin.model.RolePower;
import com.fx.admin.service.IRolePowerService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePowerServiceImpl extends BaseVOService<RolePower> implements IRolePowerService {
    @Autowired
    private IRolePowerDao rolePowerDao;

    @Override
    public List<RolePower> findByRoleId(Integer roleId) {
        return rolePowerDao.findByRoleId(roleId);
    }
}