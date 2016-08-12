package com.fx.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fx.util.Constants;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.admin.dao.IRoleDao;
import com.fx.admin.dao.IRolePowerDao;
import com.fx.admin.model.Role;
import com.fx.admin.model.RolePaginationDTO;
import com.fx.admin.model.RolePower;
import com.fx.admin.service.IRoleService;
import com.fx.crm.sys.permission.model.Permission;
import com.fx.crm.sys.permission.model.SysRolePermission;
import com.fx.crm.sys.permission.service.ISysRolePermissionService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl extends BaseVOService<Role> implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    
    @Autowired
    private IRolePowerDao rolePowerDao;

	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Override
	public int addRole(Role role) {
		if(null == role){
			return -1;
		}
		if(StringUtils.isBlank(role.getName())){
			return -2;
		}
		if(StringUtils.isBlank(role.getCreateUser())){
			return -3;
		}
		if(StringUtils.isBlank(role.getCreateIp())){
			return -4;
		}
		int result = super.doInsert(role);
		if(result > 0){
			//菜单权限增加
			List<RolePower> powerItemIdList = role.getPowerItemList();
			if(null != powerItemIdList && powerItemIdList.size() > 0){
				List<RolePower> rolePowerList = new ArrayList<RolePower>(powerItemIdList.size());
				for(RolePower rolePower : powerItemIdList){
					rolePower.setRoleId(role.getId());
				}
				rolePowerDao.addRolePowerList(powerItemIdList);
			}
			//功能权限增加
			if(null != role.getPermission()){
				List<SysRolePermission> sysRolePermissionList = createRolePermission(role, getPermissionIds(role.getPermission()), "p");
				rolePermissionService.addRolePermissionList(sysRolePermissionList);
			}
			//数据权限增加
			if(null != role.getDataRules()){
				List<SysRolePermission> sysRolePermissionList = createRolePermission(role, role.getDataRules(), "d");
				rolePermissionService.addRolePermissionList(sysRolePermissionList);
			}
		}
		return result;
	}

	private List<String> getPermissionIds(List<Permission> permission) {
		List<String> permissionIdS = new ArrayList<>();
		for(Permission p : permission){
			permissionIdS.add(p.getId());
		}
		return permissionIdS;
	}

	@Override
	public int updateRole(Role role) {
		if(null == role){
			return -1;
		}
		if(StringUtils.isBlank(role.getName())){
			return -2;
		}
		if(StringUtils.isBlank(role.getUpdateUser())){
			return -3;
		}
		if(StringUtils.isBlank(role.getUpdateIp())){
			return -4;
		}
		int result = super.doUpdateById(role);
		rolePowerDao.deleteByRoleId(role.getId());
		if(result > 0){
			rolePermissionService.logicDelete(role);
			//功能权限增加
			if(null != role.getPermission()){
				List<SysRolePermission> sysRolePermissionList = createRolePermission(role, getPermissionIds(role.getPermission()), "p");
				rolePermissionService.addRolePermissionList(sysRolePermissionList);
			}
			//数据权限增加
			if(null != role.getDataRules()){
				List<SysRolePermission> sysRolePermissionList = createRolePermission(role, role.getDataRules(), "d");
				rolePermissionService.addRolePermissionList(sysRolePermissionList);
			}

			List<RolePower> rolePowerList = role.getPowerItemList();
			rolePowerDao.addRolePowerList(rolePowerList);
		}
		return result;
	}

	private List<SysRolePermission> createRolePermission(Role role, List<String> ids, String permissionType){
		String ip = role.getUpdateIp();
		String userid = role.getUpdateUser();
		Date now = new Date(System.currentTimeMillis());
		List<SysRolePermission> rolePermissionsList = new ArrayList<SysRolePermission>();
		for(String pid : ids){
			SysRolePermission srp = new SysRolePermission();
			srp.setId(UUID.randomUUID().toString());
			srp.setPermissionId(pid);
			srp.setRoleId("" + role.getId());
			srp.setCreateIp(ip);
			srp.setCreateTime(now);
			srp.setCreateUser(userid);
			srp.setUpdateIp(ip);
			srp.setUpdateTime(now);
			srp.setUpdateUser(userid);
			srp.setStatus("0");
			srp.setPermissionType(permissionType);
			rolePermissionsList.add(srp);
		}
		return rolePermissionsList;
	}

	@Override
	public int deleteRole(Role role) {
		return roleDao.deleteByUpdate(role);
	}

	@Override
	public List<Role> findAllRole() {
		return super.findAll();
	}

	@Override
	public Role findWithPowerIdListById(int id) {
		Role role = super.findById(id);
		List<RolePower> powerIdList = rolePowerDao.findList("selectAllByRoleId", role.getId());
		role.setPowerItemList(powerIdList);
		return role;
	}
	
	@Override
	public Role findByName(String name){
		return roleDao.findByName(name);
	}
	
	@Override
	public PageIterator<Role> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize) {
		int totalCount = roleDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Role> list = roleDao.queryByCondition(params);
		PageIterator<Role> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}

	@Override
	public List<Role> findAllRoleNoIb() {
		return roleDao.findAllRoleNoIb();
	}

	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub
		roleDao.deleteByid(id);
	}

	@Override
	public void stapar(int id, int status) {
		// TODO Auto-generated method stub
		roleDao.stapar(id, status);
	}

	@Override
	public List<Role> findAlltable() {
		// TODO Auto-generated method stub
		return roleDao.findAlltable();
	}

	@Override
	public List<Role> findAlltable2() {
		// TODO Auto-generated method stub
		return roleDao.findAlltable2();
	}

	@Override
	public int findMaxlevel() {
		// TODO Auto-generated method stub
		return roleDao.findMaxlevel();
	}

	@Override
	public int findCountID() {
		// TODO Auto-generated method stub
		return roleDao.findCountID();
	}

	@Override
	public List<Role> getRolesByInnerSales(HashMap<String, Object> dateUid) {
		return roleDao.getRolesByInnerSales(dateUid);
	}

	@Override
	public Role getRoleByHier(HashMap<String, Object> hireInner) {
		return roleDao.getRoleByHier(hireInner);
	}

	@Override
	public List<Permission> getPermissionByRoleId(Integer id) {
		return roleDao.getMenuPermissions(id);
	}

	@Override
	public List<String> getDataRulesByRoleId(Integer id) {
		return roleDao.getMenuDataRules(id);
	}

	@Override
	public List<RolePower> getRolePowerByRoleId(int userId) {
		return roleDao.getPowerItemList(userId);
	}

	@Override
	public Integer getTopRoleId() {
		return roleDao.getTopRoleId();
	}

	@Override
	public int findMinLevel() {
		return roleDao.findMinLevel();
	}

    @Override
    public List<Role> getRolesByRoleId(int roleId) {
        return roleDao.getRolesByRoleId(roleId);
    }

    @Override
    public Integer getNextRoleId(int userId) {
        return roleDao.getNextRoleId(userId);
    }

    @Override
    public List<Role> getInnerRoles() {
        Role role = new Role();
        role.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
        return roleDao.getInnerRoles(role);
    }

	@Override
	public List<Role> findByOrgId(String orgId) {
		return roleDao.findByOrgId(orgId);
	}

    @Override
    public List<Role> getSubRolesByRoleId(Integer id) {
        return roleDao.getSubRolesByRoleId(id);
    }

    @Override
    public List<Integer> findAllSubRoleIds(Integer id) {
        return roleDao.findAllSubRoleIds(id);
    }

    @Override
    public int deleteExtraPermissionByIds(List<Integer> roleIds, int roleId) {
        return roleDao.deleteExtraPermissionByIds(roleIds, roleId);
    }

    @Override
    public int deleteExtraPowerByIds(List<Integer> roleIds, Integer roleId) {
        return roleDao.deleteExtraPowerByIds(roleIds, roleId);
    }

    @Override
    public int getCountByNameAndRoleIdAndId(String roleName, int orgId, int id) {
        return roleDao.getCountByNameAndRoleIdAndId(roleName, orgId, id);
    }

    @Override
    public int getCountByRoleId(String roleId) {
        return roleDao.getCountByRoleId(roleId);
    }

	@Override
	public List<Integer> getAllSalesRoleId() {
		return (List<Integer>) roleDao.findList("getAllSalesRoleId",null);
	}


	@Override
    public int getCountByOrgId(String orgId) {
        return roleDao.getCountByOrgId(orgId);
    }

    /**
	 * bootstarp table 分页角色查询
	 * 
	 *
	 * @return PageIterator<Role>
	 */
	@Override
	public PageIterator<Role> pageQueryByCondition(RolePaginationDTO rolePaginationDTO) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		String roletype = rolePaginationDTO.getRoleType();
		if (StringUtils.isNotBlank(roletype)) {
			params.put("roletype", roletype);
		}
		
		String roleName = rolePaginationDTO.getRoleName();
		if (StringUtils.isNotBlank(roleName)) {
			roleName = "%" + roleName + "%";
			params.put("name", roleName);
		}
		int totalCount = roleDao.queryCountByCondition(params);
		int offset = rolePaginationDTO.getOffset();
		int pageSize = rolePaginationDTO.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Role> list = roleDao.queryByCondition(params);
		PageIterator<Role> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}
}