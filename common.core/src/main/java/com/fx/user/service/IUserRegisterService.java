package com.fx.user.service;

import com.fx.MT4.vo.MT4User;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.giftsUser.model.GiftsUser;
import com.fx.global.model.Group;
import com.fx.payment.model.UserAccount;
import com.fx.payment.model.UserMT4Account;
import com.fx.user.model.InvestExperience;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserRegisterService extends IValueObjectService<UserRegister> {
	
	/**
	 * @param mt4dataSourceType 
	 * getDemoByEmail	根据登录邮箱查询DEMO用户
	 * @param email
	 * @return UserRegister
	 * @exception 
	*/
	public UserRegister getDemoByEmail(String email, int mt4dataSourceType);
	
	/**
	 * createUserRegister	新注册一个模拟用户
	 * 	1、初始化一个UserRegister
	 *  2、初始化一个UserAccount
	 * @param userRegister
	 * @return int	1:success 	0:fail
	 * @exception
	*/
	public int createDemoUserRegister(UserRegister userRegister,UserAccount userAccount,int mt4dataSourceType);


    /**
     * @param mt4dataSourceType
     * getLiveByEmail	根据登录邮箱查询用户
     * @param email
     * @return UserRegisterLogin
     * @exception
     */
    public UserRegister getLiveByEmail(String email);

	
	/**
	 * createUserRegister	新注册一个真实用户
	 * 	1、初始化一个UserRegister
	 * @param userRegister
	 * @param userAccount
	 * @return int	1:success 	0:fail
	 * @exception 
	*/
	public int createUserRegister(UserRegister userRegister);
	
	/**
	 * 注册--完善个人信息
	 * @param userId
	 * @param accountType
	 * @param user
	 * @param investexperience
	 * @return
	 */
	public int createRegisterLiveInfo(String password,Integer userId,Integer accountType,User user,InvestExperience investexperience,String ip);
	
	/**
	 * getByIdList	根据用户ID批量获取用户信息
	 * @param idList
	 * @return List<UserRegister>
	 * @exception 
	*/
	public List<UserRegister> getByIdList(List<Integer> idList);
	
	/**
	 * getMT4LiveGroupList	获取该公司的Live组信息
	 * @param null
	 * @return List<Group>
	 * @exception 
	*/
	public List<Group> getMT4LiveGroupList();
	
	/**
	 * createLiveMT4Account	新建Live账户
	 * @param User, UserRegister, int, UserRegister
	 * @return MT4AccountVo
	 * @exception 
	*/
	public MT4User createLiveMT4Account(User user, UserRegister userRegister,String groupId,int leverage,int agentAccount,DataSourceBean dataSourceBean);
	
	/**
	 * createLiveMT4Account	新建Live账户
	 * @param User, UserRegister, int, UserRegister
	 * @return MT4AccountVo
	 * @exception 
	*/
	public MT4User createDemoMT4Account(UserRegister userRegister,DataSourceBean dataSourceBean);
	
	public PageIterator<UserRegister> pageQueryIbByCondition(int status, int pageNo, int pageSize) ;
	
	public List<UserRegister> getByWebsiteUserType(int websiteUserType);
	public PageIterator<UserRegister> pageQueryDemoByCondition(int pageNo,
			int pageSize);

	public int queryRegEmailRepeat(String email, int dataSourceType);

	//检查名字是否重复
	public int queryRegenNameRepeat(String enName);

	public PageIterator<UserRegister> pageQueryByCondition(int countryCode, int status, Pagination pagination,String dataRule,String menuId,int support);

	public void deleteById(UserRegister userRegister);

    public List<UserRegister> getAllUsers();

    public PageIterator<UserRegister> pageIbUserQueryByCondition(Pagination pagination);

	public void modifyIbUserTypeById(int id, int ibUserType);

    public UserRegister selectUserRegister(int id);

	public int selectCountByIsIbId(int isIbId);

	public List<Integer> getAllibidsByUpperIB(Integer userId,
			int mt4DatasourceType);

	public List<UserRegister> getUserRegisterByIbId(int userId);

	public UserRegister getLiveByEmailAndMt4DataSourceType(String userName, Integer mt4dataSourceType);

	public List<UserRegister> getAllSales();

	public List<UserRegister> getParentUsersByRoleIdAndDataSourceId(int roleId, int dataSourceId);
	
	/**
	 * 查找同一数据源下的sales和所有IB
	 * @param dataSourceId
	 * @return
	 */
	public List<UserRegister> getUpperIBAndSalesByDataSource(int dataSourceId);

    public List<UserRegister> getIBUsersByIbId(int ibId);

	public UserRegister getById(Integer uid, Integer dataSourceId);

    public List<UserRegister> getAllIBUserByDataSource(int dataSource);

	List<UserRegister> findUsersByOrgId(Integer id);

	List<UserRegister> getIBByParentId(Integer id, Integer datasource);

    public UserRegister getLiveByEmailAndMt4DataSourceId(String email, int mt4dataSourceId);

	public PageIterator<UserRegister> getByWebsiteUserTypeAndSearch(int websiteUserType, Pagination pagination, int support);

	public List<UserRegister> getUserRegisterByStatus(
			HashMap<String, Object> params);

	public List<UserRegister> getByWebsiteUserTypess(int value);

    public List<UserRegister> customersExportDownload(int countryCode, int status, Pagination pagination,String dataRule ,String menuId,int support);

	List<UserRegister> findNoIb();

    public Pagination getExperienceAct(Pagination pagination);

	public List<UserRegister> getAllSuperiors(Integer id, Integer dataSourceId);

	HashMap<Integer,UserRegister> getbyUids(List<GiftsUser> giftsUserList);


	public List<UserRegister> getAllSuperiorsByaccount(Integer account, Integer dataSourceId);

	public List<UserRegister> selectIBAndDemoAndPersonal(Map parms);

	List<Integer> findUidsByCondition(HashMap<String, Object> paramsUserMt4Account);

	List<Integer> findUidsByConditionTrader(HashMap<String, Object> paramsUserMt4Account);


	public List<UserRegister> getUserRegisterByName(String userName);


	public List<UserRegister> selectSalesRepeat(Map parms);

    public List<UserRegister> getAllExpUsers();

	public List<UserRegister> getUserRegisterBySuperId(Integer integer);

	public UserRegister geiInnerUserByEmail(String email);

	List<UserRegister> getByNameId(HashMap<String, Object> params);

	List<UserRegister> getUidsByCondition(int ibId);

	List<UserRegister> getByIBId(Integer id);

	List<UserRegister> getURByName(Map<String, Object> param);
	List<UserRegister> findUserIdBySqlmapConditions(Map<String, Object> param);
	List<Integer> findUserRegisterByCname(Map<String, Object> param);
	List<Integer> findUserIdByLevelPathisOne(Map<String, Object> param);

	UserRegister getUserByIbNameAndAgentName(String ibName, String agentName);

	/**
	 * 获取销售的所有上级
	 * @param id
	 * @return
     */
	List<UserRegister> getSalesManagers(Integer id);

	/**
	 * 获取内部用户的所有直客
	 * @param id
	 * @return
     */
	List<UserRegister> getSalesCustomers(Integer id);

    /**
     * 根据id获取所有下级
     * @param id
     * @return
     */
    public List<UserRegister> getAllSonUsersById(Integer id);

	List<UserRegister> getByName(HashMap<String, Object> params);

    /**
     * 获取所有的内部用户
     * @return
     */
    public List<UserRegister> getAllAdminUsers();

	public PageIterator<UserRegister> getByWebsiteUserTypeAndLikeName(
			int value, Pagination pagination,HashMap<String,Object> params);

	public List<UserRegister> getUserRegisterByLikeName(String login, int value);

	public List<UserRegister> getByWebsiteUserTypeAndLikeNameNoPage(int value,
			HashMap<String, Object> params);

	List<UserRegister> getInnerUserByOrgId(Integer orgId, String name);

	List<UserRegister> getIBUserByParentId(String queryId, Integer orgId, String name);

	List<UserRegister> getInnerUserByParentId(String queryId, Integer orgId, String name);

	List<UserRegister> getAllSonUsersBySuperId(int ibIds);

	List<UserRegister> getLeadsExist(UserRegister email);

	void updateIsIbidByIdList(List<String> idArray, Integer agent);

	public Map<Integer,UserRegister> findbyUidAndDataSource(List<Integer> idList , Integer datasource);

	/**
	 * 根据WebSiteUserType查询多个类型的客户信息
	 * @param idList webSiteUserType多条数据，用in查询
	 * @return
	 */
	public List<UserRegister> getUserRegisterByWebSiteType(List<Integer> idList);

	public List<UserRegister> findUserregisterByIdList(List<String> idArray);

    public List<UserRegister> findAllUsers();

    public List<UserRegister> getAllLivesAndAllIbs();
}