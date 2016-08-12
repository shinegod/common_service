package com.fx.crm.sys.permission.service.impl;

import java.util.List;

import com.fx.crm.sys.permission.dao.IPermissionDao;
import com.fx.crm.sys.permission.model.Permission;
import com.fx.crm.sys.permission.service.IPermissionService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseVOService<Permission> implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

	@Override
	public List<Permission> findListByMenuId(String menuid) {
		return permissionDao.findList("selectPermissionsByMenuId", menuid);
	}
}