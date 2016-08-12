package com.fx.user.dao;

import com.fx.user.model.UserRegister;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserRegisterDao extends IValueObjectDao<UserRegister> {

	/**
	 * getDemoByEmail	根据邮箱查询DEMO用户信息
	 * @param email
	 * @return UserRegister
	 * @exception 
	*/
	public UserRegister getDemoByEmail(String email,int mt4dataSourceType);
	
	/**
	 * getLiveByEmail	根据邮箱查询用户信息
	 * @param email
	 * @return UserRegister
	 * @exception 
	*/
	public UserRegister getLiveByEmail(String email);

	/**
	 * getByIdList	根据ID批量获取用户注册信息
	 * @param idList
	 * @return List<UserRegister>
	 * @exception 
	*/
	public List<UserRegister> getByIdList(List<Integer> idList);
	public int queryCountIbByCondition(Map<String, Object> params);
	public List<UserRegister> pageQueryIbByCondition(Map<String, Object> params);
	
	public List<UserRegister> getIBUserRegister(int websiteUserType);
	public int queryCountDemoByCondition(Map<String, Object> params);
	public List<UserRegister> pageQueryDemoByCondition(
			Map<String, Object> params);

	//检查邮箱是否重复
	public int queryRegEmailRepeat(String email, int dataSourceType);

	//检查名字是否重复
	public int queryRegenNameRepeat(String enName);

	public int queryCountByCondition(UserRegister userRegister);

	public List<UserRegister> queryByCondition(UserRegister userRegister);

	//根据ID删除  is_del改为1
	public void deleteById(UserRegister userRegister);

	public void modifyIbUserTypeById(int id,int ibUserType);

    public UserRegister selectUserRegister(int id);

	public int selectCountByIsIbId(int isIbId);

	public UserRegister getLiveByEmailAndMt4DataSourceType(String userName, Integer mt4dataSourceType);

	public List<UserRegister> getAllSales();

	public List<UserRegister> getParentUsersByRoleIdAndDataSourceId(int roleId, int dataSourceId);

	public List<UserRegister> getUpperIBAndSalesByDataSource(int dataSourceId);

    public List<UserRegister> getIBUsersByIbId(int ibId);

	public UserRegister getById(Integer uid, Integer dataSourceId);

	public List<UserRegister> getAllUsers();

    public List<UserRegister> getAllIBUserByDataSource(int dataSource);

	public List<UserRegister> getUserRegister(UserRegister userRegister);

	List<UserRegister> findUsersByOrgId(UserRegister user);

	List<UserRegister> getIBByParentId(UserRegister user);

    public UserRegister getLiveByEmailAndMt4DataSourceId(String email, int mt4dataSourceId);

	public Integer getUserRegisterCount(UserRegister userRegister);

    public List<UserRegister> customersExportDownload(UserRegister userRegister);

	List<UserRegister> findNoIb();

    public List<UserRegister> getExperienceAct(UserRegister userRegister);

    public int getExperienceActCount(UserRegister userRegister);

	public UserRegister selectById(Integer id);

	List<UserRegister> getbyUids(HashMap<String, Object> params);

	public List<UserRegister> selectIBAndDemoAndPersonal(Map parms);

	List<Integer> findUidsByCondition(UserRegister userRegister);

	List<Integer> findUidsByConditionTrader(UserRegister userRegister);


	public List<UserRegister> getUserRegisterByName(Map<String, Object> params);


	public List<UserRegister> selectSalesRepeat(Map parms);


    public List<UserRegister> getAllExpUsers();

	List<UserRegister> getByNameId(HashMap<String, Object> params);

	List<UserRegister> getUidsByCondition(Map<String, Object> params);

	List<UserRegister> getByIBId(Integer id);

	List<UserRegister> getURByName(Map<String, Object> param);
	List<Integer> findUserIdByLevelPathisOne(Map<String, Object> param);
	List<Integer> findUserRegisterByCname(Map<String, Object> param);
	List<UserRegister> findUserIdBySqlmapConditions(Map<String, Object> param);

	List<UserRegister> getUserByIbNameAndAgentName(Map<String, String> params);

	List<UserRegister> findCustomersByUpper(UserRegister userRegister);

	public int getUserRegisterCountBystus(UserRegister userRegister);

	public List<UserRegister> getUserRegisterStatus(UserRegister userRegister);

	List<UserRegister> findUserByEntity(UserRegister userRegister);

	List<UserRegister> findInnerUserByEntity(UserRegister userRegister);

	List<UserRegister> getLeadsExist(UserRegister email);

	public List<UserRegister> getByIdListAndDatasource(Map<String, Object> param);

	/**
	 * 根据WebSiteUserType查询多个类型的客户信息
	 * @param idList webSiteUserType多条数据，用in查询
	 * @return
	 */
	public List<UserRegister> getUserRegisterByWebSiteType(List<Integer> idList);


}