package com.fx.admin.dao.impl;

import com.fx.admin.dao.IRoleDao;
import com.fx.admin.model.Role;
import com.fx.admin.model.RolePower;
import com.fx.crm.sys.permission.model.Permission;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements IRoleDao {

    public RoleDaoImpl() {
        super(IRoleDao.class.getName());
    }

	@Override
	public int deleteByUpdate(Role role) {
		return super.doUpdate("deleteByUpdate", role);
	}

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<Role> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public Role findByName(String name) {
		return (Role) findOne("getByName", name);
	}

	@Override
	public List<Role> findAllRoleNoIb() {
		Map<String, Object> params =new HashMap<String, Object>();
		return findList("findAllRoleNoIb",params);
	}

	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub
		update("logicDeleteById", id);
		
	}

	@Override
	public void stapar(int id, int status) {
		// TODO Auto-generated method stub
		Map<String, Object> params =new HashMap<String, Object>();
		params.put("id", id);
		params.put("status", status);
		update("stapar", params);
	}

	@Override
	public List<Role> findAlltable() {
		// TODO Auto-generated method stub
		Map<String, Object> params =new HashMap<String, Object>();
		return findList("findAlltable",params);
	}

	@Override
	public List<Role> findAlltable2() {
		// TODO Auto-generated method stub
		Map<String, Object> params =new HashMap<String, Object>();
		return findList("findAlltable2",params);
	}

	@Override
	public int findMaxlevel() {
		// TODO Auto-generated method stub
		Map<String, Object> params =new HashMap<String, Object>();
		return (Integer)findOne("findmaxlevel",params);
	}

	@Override
	public int findCountID() {
		// TODO Auto-generated method stub
		Map<String, Object> params =new HashMap<String, Object>();
		return (Integer)findOne("findwbuser",params);
	}
	@Override
	public List<Role> getRolesByInnerSales(HashMap<String, Object> dateUid) {
		return findList("getRolesByInnerSales", dateUid);
	}

	@Override
	public Role getRoleByHier(HashMap<String, Object> hireInner) {
		return (Role) findOne("getRoleByHier", hireInner);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getMenuPermissions(Integer id) {
		Map<String, Object> parameter =new HashMap<String, Object>();
		parameter.put("id", id);
		return super.findList("getMenuPermissions", parameter);
	}

	@Override
	public List<String> getMenuDataRules(Integer id) {
		Map<String, Object> parameter =new HashMap<String, Object>();
		parameter.put("id", id);
		return super.findList("getMenuDataRules", parameter);
	}

	@Override
	public List<RolePower> getPowerItemList(int roleId) {
		Map<String, Object> parameter =new HashMap<String, Object>();
		parameter.put("id", roleId);		
		return super.findList("getPowerItemList", parameter);
	}

	@Override
	public Integer getTopRoleId() {
		return (Integer)findOne("getTopRoleId", null);
	}

	@Override
	public int findMinLevel() {
		Map<String, Object> params =new HashMap<String, Object>();
		return (Integer)findOne("findminlevel", params);
	}

    @Override
    public List<Role> getRolesByRoleId(int roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        return findList("getRolesByRoleId", params);
    }

    @Override
    public Integer getNextRoleId(int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return (Integer) findOne("getNextRoleId", params);
    }

    @Override
    public List<Role> getInnerRoles(Role role) {
        return super.findList("getInnerRoles", role);
    }

	@Override
	public List<Role> findByOrgId(String orgId) {
		return findList("findByOrgId", orgId);
	}

    @Override
    public int getCountByOrgId(String orgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orgId", orgId);
        return (Integer) findOne("getCountByOrgId", params);
    }

    @Override
    public List<Role> getSubRolesByRoleId(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return super.findList("getSubRolesByRoleId", params);
    }

    @Override
    public List<Integer> findAllSubRoleIds(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return super.findList("findAllSubRoleIds", params);
    }

    @Override
    public int deleteExtraPermissionByIds(List<Integer> roleIds, int roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleIds", roleIds);
        params.put("roleId", roleId);
        return super.doUpdate("deleteExtraPermissionByIds", params);
    }

    @Override
    public int deleteExtraPowerByIds(List<Integer> roleIds, Integer roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleIds", roleIds);
        params.put("roleId", roleId);
        return super.doDelete("deleteExtraPowerByIds", params);
    }

    @Override
    public int getCountByNameAndRoleIdAndId(String roleName, int orgId, int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        params.put("orgId", orgId);
        params.put("id", id);
        return (Integer) findOne("getCountByNameAndRoleIdAndId", params);
    }

    @Override
    public int getCountByRoleId(String roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        return (Integer) findOne("getCountByRoleId", params);
    }

}