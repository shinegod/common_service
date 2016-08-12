package com.fx.admin.service;

import com.fx.admin.model.Admin;
import com.fx.user.model.User;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.List;

public interface IAdminService extends IValueObjectService<Admin> {
	/**
	 * addAdmin	增加一个管理员记录
	 * @param admin
	 * @return int
	 * @exception 
	*/
	public int addAdmin(Admin admin);
	
	/**
	 * updateAdmin	修改管理员信息
	 * @param admin
	 * @return int
	 * @exception 
	*/
	public int updateAdmin(Admin admin);
	
	/**
	 * delAdmin	删除一条管理员记录
	 * @param admin
	 * @return int
	 * @exception 
	*/
	public int deleteAdmin(Admin admin);
	
	/**
	 * findAllAdmin	获取所有的管理员信息
	 * @return List<Admin>
	 * @exception 
	*/
	public List<Admin> findAllAdmin();
	
	/**
	 * findById	根据ID获取一条管理员信息
	 * @param id
	 * @return Admin
	 * @exception 
	*/
	public Admin findById(int id);
	
	/**
	 * findByName	根据名称获取管理员信息
	 * @param name
	 * @return Admin
	 * @exception 
	*/
	public Admin findByName(String name);
	
	/**
	 * pageQuery	分页查询管理员信息
	 * @param name
	 * @param status
	 * @param roleId
	 * @param page
	 * @param pageSize
	 * @return PageIterator<Admin>
	 * @exception 
	*/
	public PageIterator<Admin> pageQuery(String name, int status, int roleId, int page, int pageSize);

	public PageIterator<Admin> pageQuerySale(String name, int status,
			int pageNo, int pageSize);

	public List<Admin> findAllSales();
	
	//根据用户名获得密码
	public String oldpassById(int id);
	
	public List<Admin> getRoleName();

	public List<Admin> getAdminListByRolesId(List<Integer> roleIdList);

	public List<User> findAllUsers();

	public Admin findByUserId(int userId);

	public Admin findByNameOrEmail(String username);

	public List<Admin> getAdminListByNameLike(String login);
}