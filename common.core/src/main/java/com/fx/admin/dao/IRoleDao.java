package com.fx.admin.dao;

import com.fx.admin.model.Role;
import com.fx.admin.model.RolePower;
import com.fx.crm.sys.permission.model.Permission;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoleDao extends IValueObjectDao<Role> {

	public int deleteByUpdate(Role role);
	
	/**
	 * queryCountByCondition	分页查询时，查询总数量
	 * @param params
	 * @return int
	 * @exception 
	*/
	public int queryCountByCondition(Map<String, Object> params);

	/**
	 * queryByCondition	分页查询时，查询每页的数据
	 * @param params
	 * @return List<Role>
	 * @exception 
	*/
	public List<Role> queryByCondition(Map<String, Object> params);
	
	/**
	 * findByName	根据角色名称获取角色信息
	 * @param name
	 * @return Role
	 * @exception 
	*/
	public Role findByName(String name);

	public List<Role> findAllRoleNoIb();
	
	public void deleteByid(int id);
	
	public void stapar(int id,int status);
	
	public  List<Role> findAlltable();
	
	public  List<Role> findAlltable2();
	
	public int findMaxlevel();
	
	public int findCountID();

	/**
	 * 查询出所有的公司内部销售
	 * @param dateUid
	 * @return
	 */
	public List<Role> getRolesByInnerSales(HashMap<String, Object> dateUid);

	public Role getRoleByHier(HashMap<String, Object> hireInner);
	
	/**
	 * 根据role id 查询所有Permission对象
	 * @param id
	 * @return
	 */
	public List<Permission> getMenuPermissions(Integer id);

	/**
	 * 根据role id 查询所有 数据权限和操作权限
	 * @param id
	 * @return
	 */
	public List<String> getMenuDataRules(Integer id);
	
	/**
	 * <p>this is a test document comments</p>
	 * @param userId
	 * @return
	 */
	public List<RolePower> getPowerItemList(int userId);


	public Integer getTopRoleId();

	public int findMinLevel();

    public List<Role> getRolesByRoleId(int roleId);

    public Integer getNextRoleId(int userId);

    public List<Role> getInnerRoles(Role role);

	List<Role> findByOrgId(String orgId);

    public int getCountByOrgId(String orgId);

    public List<Role> getSubRolesByRoleId(Integer id);

    public List<Integer> findAllSubRoleIds(Integer id);

    public int deleteExtraPermissionByIds(List<Integer> roleIds, int roleId);

    public int deleteExtraPowerByIds(List<Integer> roleIds, Integer roleId);

    public int getCountByNameAndRoleIdAndId(String roleName, int orgId, int id);

    public int getCountByRoleId(String roleId);
}