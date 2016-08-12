package com.fx.admin.dao;

import com.fx.admin.model.Admin;
import com.fx.user.model.User;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IAdminDao extends IValueObjectDao<Admin> {
	
	/**
	 * deleteByUpdate	更新管理员为删除状态
	 * @param admin
	 * @return int
	 * @exception 
	*/
	public int deleteByUpdate(Admin admin);

	/**
	 * findByName	根据名称查询管理员信息
	 * @param name
	 * @return Admin
	 * @exception 
	*/
	public Admin findByName(String name);
	
	public int getQueryCount(String name, int status, int roleId);
	
	public List<Admin> queryByCondition(String name, int status, int roleId, int offset, int limit);

	public int getQuerySaleCount(String name, int status);

	public List<Admin> querySaleByCondition(String name, int status, int offset, int pageSize);

	public List<Admin> findAllSales();
	
	public String oldpassById(int id);

	public List<Admin> getRoleName();

	public List<Admin> getAdminListByRolesId(List<Integer> roleIdList);

	public List<User> findAllUsers();

	public Admin findByUserId(int userId);
}