package com.fx.crm.sys.permission.service;

import com.fx.admin.model.Role;
import com.fx.crm.sys.permission.model.SysRolePermission;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ISysRolePermissionService extends IValueObjectService<SysRolePermission> {
    public int addRolePermissionList(List<SysRolePermission> sysRolePermissionList);

    public int logicDelete(Role role);

    public List<SysRolePermission> selectDataRuleByMenuId(String menuId,String roleId);

}