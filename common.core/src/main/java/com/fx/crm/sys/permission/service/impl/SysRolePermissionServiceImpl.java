package com.fx.crm.sys.permission.service.impl;

import com.fx.admin.model.Role;
import com.fx.crm.sys.permission.dao.ISysRolePermissionDao;
import com.fx.crm.sys.permission.model.SysRolePermission;
import com.fx.crm.sys.permission.service.ISysRolePermissionService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SysRolePermissionServiceImpl extends BaseVOService<SysRolePermission> implements ISysRolePermissionService {
    @Autowired
    private ISysRolePermissionDao sysRolePermissionDao;

    @Override
    public int addRolePermissionList(List<SysRolePermission> sysRolePermissionList) {

        return sysRolePermissionDao.addRolePermission("insertList", sysRolePermissionList);
    }

    @Override
    public int logicDelete(Role role) {
        return sysRolePermissionDao.doUpdate("logicDeleteByRole",role);
    }

    @Override
    public List<SysRolePermission> selectDataRuleByMenuId(String menuId,String roleId) {
        return sysRolePermissionDao.selectDataRuleByMenuId(menuId,roleId);
    }

}