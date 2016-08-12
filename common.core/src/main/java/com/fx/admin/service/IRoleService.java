package com.fx.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.admin.model.Role;
import com.fx.admin.model.RolePaginationDTO;
import com.fx.admin.model.RolePower;
import com.fx.crm.sys.permission.model.Permission;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoleService extends IValueObjectService<Role> {
	
	/**
	 * addRole	增加一个角色信息
	 * @param role
	 * @return int
	 * @exception 
	*/
	public int addRole(Role role);
	
	/**
	 * updateRole	修改角色信息
	 * @param role
	 * @return int
	 * @exception 
	*/
	public int updateRole(Role role);
	
	/**
	 * deleteRole	删除角色信息
	 * @param role
	 * @return int
	 * @exception 
	*/
	public int deleteRole(Role role);
	
	/**
	 * findAllRole	获取所有的角色信息
	 * @return List<Role>
	 * @exception 
	*/
	public List<Role> findAllRole();
	
	/**
	 * findWithPowerIdListById	根据ID获取角色信息
	 * @param id
	 * @return Role
	 * @exception 
	*/
	public Role findWithPowerIdListById(int id);
	
	/**
	 * findByName	根据名称查询角色
	 * @return Role
	 * @exception 
	*/
	public Role findByName(String name);
	
	/**
	 * pageQueryByCondition	分页查询角色信息
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return PageIterator<Role>
	 * @exception 
	*/
	public PageIterator<Role> pageQueryByCondition(	Map<String, Object> params, int pageNo, int pageSize);
	
	/**
	 * bootstarp table 分页角色查询
	 * 
	 * @param  pagination 分页对象
	 * @return PageIterator<Role>
	 */
	public PageIterator<Role> pageQueryByCondition(RolePaginationDTO rolePaginationDTO);

	public List<Role> findAllRoleNoIb();
	
	//根据id删除角色
	public void deleteByid(int id);
	
	//修改状态 启动 暂停
	public void stapar(int id,int status);
	
	//查询内部角色
	public List<Role> findAlltable();
	
	//查询外部数据
	public List<Role> findAlltable2();
	
	//查询最大level
	public int findMaxlevel();
	
	//查询外部角色的条数
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
	public List<Permission> getPermissionByRoleId(Integer id);

	/**
	 * 根据role id 查询所有 数据权限和操作权限
	 * @param id
	 * @return
	 */
	public List<String> getDataRulesByRoleId(Integer id);
	
	/**
	 * <p>this is a test document comments</p>
	 * @param userId
	 * @return
	 */
	public List<RolePower> getRolePowerByRoleId(int userId);
	
	public Integer getTopRoleId();

	public int findMinLevel();

    public List<Role> getRolesByRoleId(int roleId);

    public Integer getNextRoleId(int userId);

    public List<Role> getInnerRoles();

    public int getCountByOrgId(String orgId);

	List<Role> findByOrgId(String orgId);

    public List<Role> getSubRolesByRoleId(Integer id);

    public List<Integer> findAllSubRoleIds(Integer id);

    public int deleteExtraPermissionByIds(List<Integer> roleIds, int roleId);

    public int deleteExtraPowerByIds(List<Integer> roleIds, Integer roleId);

    public int getCountByNameAndRoleIdAndId(String roleName, int orgId, int id);

    public int getCountByRoleId(String roleId);

	public List<Integer> getAllSalesRoleId();
}