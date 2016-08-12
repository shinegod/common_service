package com.fx.admin.service;

import com.fx.admin.model.RolePower;

import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IRolePowerService extends IValueObjectService<RolePower> {
    List<RolePower> findByRoleId(Integer roleId);
}