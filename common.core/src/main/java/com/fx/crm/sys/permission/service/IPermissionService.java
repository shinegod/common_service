package com.fx.crm.sys.permission.service;

import java.util.List;

import com.fx.crm.sys.permission.model.Permission;

import mybatis.framework.core.service.IValueObjectService;

public interface IPermissionService extends IValueObjectService<Permission> {

	List<Permission> findListByMenuId(String menuid);
}