package com.fx.user.service.impl;

import com.fx.MT4.util.MT4AccountUtil;
import com.fx.MT4.util.MT4Configuration;
import com.fx.MT4.vo.MT4User;
import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.crm.sys.org.model.Organization;
import com.fx.crm.sys.org.service.IOrganizationService;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.enums.IsDelEnum;
import com.fx.giftsUser.model.GiftsUser;
import com.fx.global.model.Group;
import com.fx.global.service.IGroupService;
import com.fx.global.service.IUidGeneratorService;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserAccount;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserAccountService;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.user.dao.IUserRegisterDao;
import com.fx.user.enums.UserAccountTypeEnum;
import com.fx.user.enums.UserAdminTypeEnum;
import com.fx.user.enums.UserRegisterStatusEnum;
import com.fx.user.enums.UserStatusEnum;
import com.fx.user.model.InvestExperience;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IInvestExperienceService;
import com.fx.user.service.IUserRegisterService;
import com.fx.user.service.IUserService;
import com.fx.user.util.UserUtils;
import com.fx.util.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserRegisterServiceImpl extends BaseVOService<UserRegister> implements IUserRegisterService {
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceImpl.class);

	@Autowired
    private IUserRegisterDao userRegisterDao;

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IUserService userService;

    @Autowired
	private IAdminService adminService;

	@Autowired
	private IGroupService groupService;

    @Autowired
    private IInvestExperienceService investExperienceService;

	@Autowired
	private IUidGeneratorService uidGeneratorService;

	@Autowired
	private IMt4UsersService mt4UsersService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserMT4AccountService userMT4AccountService;

	@Autowired
	private IOrganizationService organizationService;

	public UserRegisterServiceImpl() {
		super();
	}

	@Override
	public UserRegister getDemoByEmail(String email,int mt4dataSourceType) {
		return userRegisterDao.getDemoByEmail(email, mt4dataSourceType);
	}

	@Override
	public UserRegister getLiveByEmail(String email) {
		return userRegisterDao.getLiveByEmail(email);
	}

	@Override
	public int createDemoUserRegister(UserRegister userRegister, UserAccount userAccount,int mt4dataSourceType ) {
		if(null == userRegister){
			return -1;	//参数为空
		}
		if(StringUtils.isBlank(userRegister.getEmail())){
			return -2;	//邮箱为空
		}
		if(StringUtils.isBlank(userRegister.getPassword())){
			return -3;	//密码为空
		}
		if(StringUtils.isBlank(userRegister.getCreateIp())){
			return -4;	//操作者IP为空
		}
		UserRegister userReg;
		if(userRegister.getWebsiteUserType()==1){
			userReg =  getDemoByEmail(userRegister.getEmail(),mt4dataSourceType);
		}else{
			userReg =  getLiveByEmailAndMt4DataSourceType(userRegister.getEmail(),mt4dataSourceType);
		}

		if (userReg != null && userReg.getId() != null && userReg.getId().intValue() != 0){
			return -5; //该邮箱已存在
		}

		UserRegister ur = new UserRegister();
		ur.setEmail(userRegister.getEmail());
		String password = userRegister.getPassword();
		String entryPassword = CodeUtil.encryptPassword(password);
		ur.setPassword(entryPassword);
		ur.setPhoneNum(userRegister.getPhoneNum());
		ur.setAdminType(UserAdminTypeEnum.Customer.getValue());
		ur.setLastLoginTime(null);
		ur.setLoginNum(0);
		ur.setCnName(userRegister.getCnName());
		ur.setEnName(userRegister.getEnName());
		ur.setExExpression(userRegister.getExExpression());
		ur.setFirstLanguage(userRegister.getFirstLanguage());
		ur.setWhereKown(userRegister.getWhereKown());
		ur.setStatus(UserRegisterStatusEnum.INIT.getValue());
		ur.setWebsiteUserType(userRegister.getWebsiteUserType());
		ur.setCreateUser("");
		ur.setIs_ibId(userRegister.getIs_ibId());
		// 如果上级为空  给默认上级
        if (ur.getIs_ibId() == 0) {
        	ur.setIs_ibId(Constants.DEFAULT_AGENT_ID);
        }
		ur.setCreateTime(DateUtil.getCurrentTime());
		ur.setCreateIp(userRegister.getCreateIp());
		ur.setUpdateUser("");
		ur.setUpdateTime(DateUtil.getCurrentTime());
		ur.setUpdateIp(userRegister.getCreateIp());
		ur.setIsDel(IsDelEnum.NO.getValue());
		ur.setMt4DatasourceType(userRegister.getMt4DatasourceType());
		ur.setMt4DataSourceId(userRegister.getMt4DataSourceId());
		ur.setAgent_mt4Account(userRegister.getAgent_mt4Account());
		int result = userRegisterDao.doInsert("insert", ur);
		logger.debug("######userRegisterDao.doInsert,result:{}",result);
		if(result <= 0){
			return -1;
		}
		userAccount.setUid(ur.getId());
		userAccount.setCreateUser(String.valueOf(ur.getId()));
		userAccount.setCreateIp(ur.getCreateIp());
		userAccount.setIb_id(0);
		try {
			logger.debug("######userAccountService.createUserAccount before,userAccount:{}",new Gson().toJson(userAccount));
			result = userAccountService.createUserAccount(userAccount);
		} catch (PayException e) {
			logger.error("######userAccountService.createUserAccount error,userAccount:{}",new Gson().toJson(userAccount));
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		if(result <= 0){
			return -1;
		}
		return ur.getId();
	}


	@Override
	public int createUserRegister(UserRegister userRegister) {
		if(null == userRegister){
			return -1;	//参数为空
		}
		if(StringUtils.isBlank(userRegister.getEmail())){
			return -2;	//邮箱为空
		}
		if(StringUtils.isBlank(userRegister.getPassword())){
			return -3;	//密码为空
		}
		if(StringUtils.isBlank(userRegister.getCreateIp())){
			return -4;	//操作者IP为空
		}

		UserRegister userReg;
		if(userRegister.getWebsiteUserType()==1){
			userReg =  getDemoByEmail(userRegister.getEmail(),userRegister.getMt4DatasourceType());
		}else{
			userReg =  getLiveByEmailAndMt4DataSourceType(userRegister.getEmail(),userRegister.getMt4DatasourceType());
		}

		if (userReg != null && userReg.getId() != null && userReg.getId().intValue() != 0){
			return -5; //该邮箱已存在
		}
		UserRegister ur = new UserRegister();
		ur.setEmail(userRegister.getEmail());
		ur.setPassword(userRegister.getPassword());
		ur.setSalt(userRegister.getSalt());
		ur.setPhoneNum(userRegister.getPhoneNum());
		ur.setAdminType(UserAdminTypeEnum.Customer.getValue());
		ur.setLastLoginTime(null);
		ur.setLoginNum(1);
		ur.setStatus(UserRegisterStatusEnum.INIT.getValue());
		ur.setCreateUser("myself");
		ur.setCreateTime(DateUtil.getCurrentTime());
		ur.setCreateIp(userRegister.getCreateIp());
		ur.setUpdateUser("myself");
		ur.setCnName(userRegister.getCnName());
		ur.setWhereKown(userRegister.getWhereKown());
		ur.setExExpression(userRegister.getExExpression());
		ur.setEnName(userRegister.getEnName());
		ur.setIbName(userRegister.getIbName());
		ur.setIs_ibId(userRegister.getIs_ibId());
		// 如果上级为空  给默认上级
        if (ur.getIs_ibId() == 0) {
        	ur.setIs_ibId(Constants.DEFAULT_AGENT_ID);
        }
		ur.setIs_ibName(userRegister.getIs_ibName());
		ur.setIs_supervision(userRegister.getIs_supervision());
		ur.setIs_trading(userRegister.getIs_trading());
		ur.setTradingNum(userRegister.getTradingNum());
		ur.setUpdateTime(DateUtil.getCurrentTime());
		ur.setUpdateIp(userRegister.getCreateIp());
		ur.setMt4DatasourceType(userRegister.getMt4DatasourceType());
		ur.setMt4DataSourceId(userRegister.getMt4DataSourceId());
		ur.setWebsiteUserType(userRegister.getWebsiteUserType());
		ur.setIsDel(IsDelEnum.NO.getValue());
		ur.setIs_ib(userRegister.getIs_ib());
		ur.setSellId(0);
		ur.setAgent_mt4Account(userRegister.getAgent_mt4Account());
		ur.setIbUserType(userRegister.getIbUserType());
		ur.setComment(userRegister.getComment());
		ur.setTitle(userRegister.getTitle());
		int urResult = userRegisterDao.doInsert("insert", ur);
		logger.debug("######userRegisterDao.doInsert,urResult:{}",urResult);
		if(urResult <= 0){
			return -1;
		}
		//创建用户账户
		// 生成交易编码并存入userAccount表,例如:“xxxx0000000001";
		String affiliateCode = UserUtils.createTradeCode(ur.getId());
		UserAccount userAccount = new UserAccount();
		userAccount.setTradeCode(affiliateCode);
		userAccount.setUid(ur.getId());
		userAccount.setMt4Account(0);
		userAccount.setMt4GroupId("0");
		userAccount.setMt4AccountType(0);
		userAccount.setFundsIntoNumber(0);
		userAccount.setFundsOutNumber(0);
		userAccount.setIb_id(ur.getIs_ibId());
		userAccount.setCreateTime(DateUtil.getCurrentTime());
		userAccount.setCreateIp(userRegister.getCreateIp());
		userAccount.setCreateUser(String.valueOf(ur.getId()));
		userAccount.setUpdateIp(userRegister.getCreateIp());
		userAccount.setUpdateTime(DateUtil.getCurrentTime());
		userAccount.setUpdateUser(String.valueOf(ur.getId()));
		int uaResult = 0;
		try {
			logger.debug("######userAccountService.createUserAccount before,userAccount:{}",new Gson().toJson(userAccount));
			uaResult = userAccountService.createUserAccount(userAccount);
		} catch (PayException e) {
			logger.error("######userAccountService.createUserAccount error,userAccount:{}",new Gson().toJson(userAccount));
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		if (uaResult <= 0) {
			return -1;
		}
		return ur.getId();
	}

	@Override
	public int createRegisterLiveInfo(String password,Integer userId, Integer accountType, User user, InvestExperience investexperience,String ip) {
		if (accountType == null || accountType.intValue() == 0) {
			return -3;//账户类型错误
		}
		//verify user
		int userVerifyCode = verifyUser(user);
		if (userVerifyCode < 0) {
			return -1;//用户信息不全
		}
		//verify InvestExperience
		int investexperienceVerifyCode = verifyInvestExperience(investexperience);
		if (investexperienceVerifyCode < 0) {
			return -2;//投资经验信息不全
		}
		//insert userInfo
		UserAccount userAccount = null;
		try {
			logger.debug("######getUserAccountByUid before ,userid:{}", userId);
			userAccount = userAccountService.getUserAccountByUid(userId);
		} catch (PayException e) {
			logger.error("######etUserAccountByUid error,userid:{}", userId);
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		user.setUserId(userId);
		user.setIsDel(IsDelEnum.NO.getValue());
		user.setCreateTime(DateUtil.getCurrentTime());
		user.setCreateIp(ip);
		user.setCreateUser("" + userId);
		user.setUpdateUser("" + userId);
		user.setUpdateTime(DateUtil.getCurrentTime());
		user.setUpdateIp(ip);
		user.setIb_Id(userAccount.getIb_id());
		user.setStatus(UserStatusEnum.INIT_AUDIT.getValue());
		User userInDB = userService.getByUid(userId);


		if(null == userInDB){
			logger.debug("######userService doInsert before,user :{}",new Gson().toJson(user));
			int userResult = userService.doInsert(user);
			if (userResult <= 0) {
				return userResult;
			}
		}else{
			user.setId(userInDB.getId());
			logger.debug("######userService doUpdateById before,user :{}",new Gson().toJson(user));
			userService.doUpdateById(user);
		}
		//insert InvestExperience
		InvestExperience ie = investExperienceService.getByUid(userId);
		investexperience.setUserId(userId);
		investexperience.setIsDel(IsDelEnum.NO.getValue());
		investexperience.setCreateTime(DateUtil.getCurrentTime());
		investexperience.setCreateIp(ip);
		investexperience.setUpdateTime(DateUtil.getCurrentTime());
		investexperience.setUpdateIp(ip);
		investexperience.setCreateUser(""+userId);
		investexperience.setUpdateUser("" + userId);
		if(null == ie){
			logger.debug("######investExperienceService doInsert before,investexperience :{}",new Gson().toJson(investexperience));
			int investexperienceResult = investExperienceService.doInsert(investexperience);
			if (investexperienceResult <= 0 ){
				return investexperienceResult;
			}
		}else{
			investexperience.setId(ie.getId());
			logger.debug("######investExperienceService doUpdateById before,investexperience :{}",new Gson().toJson(investexperience));
			investExperienceService.doUpdateById(investexperience);
		}
		//update UserAccount

		if (userAccount == null || userAccount.getId() == null || userAccount.getId().intValue() == 0) {
			return -99;//第一步的操作未成功
		}
//		userAccount.setMt4AccountType(accountType);
		userAccount.setUpdateIp(ip);
		userAccount.setUpdateTime(DateUtil.getCurrentTime());
		userAccount.setUpdateUser(String.valueOf(userId));
		userAccount.setIb_id(user.getIb_Id());
		logger.debug("######userAccountService doInsert doUpdateById before,userAccount :{}", new Gson().toJson(userAccount));
		int userAccountResult = userAccountService.doUpdateById(userAccount);
		logger.debug("######userAccountService doInsert doUpdateById after,userAccountResult :{}", userAccountResult);
		if (userAccountResult <= 0) {
			return userAccountResult;
		}
//		if(user.getIs_ib()==1){
//			UserRegister userRegister = findById(user.getUserId());
//			String md5Password = CodeUtil.encryptAdminPassword(password);//生成MD5密码
//			Admin addAdmin = new Admin();
//			addAdmin.setName(userRegister.getEmail().trim());
//			addAdmin.setPassword(md5Password);
//			addAdmin.setRoleId(5);
//			addAdmin.setLastLoginTime("");
//			addAdmin.setLoginNum(0);
//			addAdmin.setStatus(AdminStatusEnum.NORMAL.getValue());
//			addAdmin.setCreateUser(String.valueOf(user.getId()));
//			addAdmin.setCreateTime(DateUtil.getCurrentTime());
//			addAdmin.setCreateIp(user.getCreateIp());
//			addAdmin.setUpdateUser(String.valueOf(user.getId()));
//			addAdmin.setUpdateTime(DateUtil.getCurrentTime());
//			addAdmin.setUpdateIp(user.getCreateIp());
//			addAdmin.setIsDel(IsDelEnum.NO.getValue());
//			addAdmin.setIb_Id(user.getId());
//			Admin testHasAdmin = adminService.findByName(userRegister.getEmail().trim());
//			if(testHasAdmin!=null){
//				if(!md5Password.equals(testHasAdmin.getPassword())){
//					adminService.addAdmin(addAdmin);
//				}
//			}else{
//				adminService.addAdmin(addAdmin);
//			}
//		}
		return 1;
	}

	@Override
	public List<UserRegister> getByIdList(List<Integer> idList){
		if(null == idList || idList.size() <= 0){
			return null;
		}
		return userRegisterDao.getByIdList(idList);
	}

	private int verifyInvestExperience(InvestExperience investexperience) {
		// TODO to be continued
		return 0;
	}


	private int verifyUser(User user) {
		if (user == null) {
			return -1;//无基本用户信息
		}
//		if (StringUtils.isBlank(user.getFirstName())) {
//			return -2;//无名信息
//		}
//		if (StringUtils.isBlank(user.getLastName())) {
//			return -3;//无姓信息
//		}
		return 0;
	}

	@Override
	public List<Group> getMT4LiveGroupList() {
		return groupService.getMT4LiveGroupList(MT4Configuration.companyId);
	}

	@Override
	public MT4User createLiveMT4Account(User user,UserRegister userRegister, String groupId, int leverage , int agentAccount,DataSourceBean dataSourceBean) {
//		String groupNameMT4=groupDao.findGroupByID(groupId).getGroupNameMT4();
		multiDataSourceSet(dataSourceBean.getId());
		int login=uidGeneratorService.selectNextIdLive(MT4Configuration.companyId);
		while(mt4UsersService.repeatMT4Account(login)!=0){
			login = login +1 ;
		}
		uidGeneratorService.updateNextIdLive(MT4Configuration.companyId,login+1);
		MT4User mT4User=MT4AccountUtil.createLiveMT4Account(user, userRegister, groupId, leverage, login,agentAccount, dataSourceBean);
		return mT4User;
	}

	@Override
	public MT4User createDemoMT4Account(UserRegister userRegister,DataSourceBean dataSourceBean) {
//		int login=uidGeneratorService.selectNextIdDemo(MT4Configuration.companyId);
//		uidGeneratorService.updateNextIdDemo(MT4Configuration.companyId,login+1);
		//解决demo账户login冲突的情况，让mt4自动生成demo账户的login
		int login = 0;
		return MT4AccountUtil.createDemoMT4Account(userRegister,login,dataSourceBean);
	}

	@Override
	public PageIterator<UserRegister> pageQueryIbByCondition(int status, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = userRegisterDao.queryCountIbByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserRegister> userList = userRegisterDao.pageQueryIbByCondition(params);
		PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}
	@Override
	public List<UserRegister> getByWebsiteUserType(int websiteUserType) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(websiteUserType);
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		return userRegisterDao.getUserRegister(userRegister);
	}
	@Override
	public PageIterator<UserRegister> getByWebsiteUserTypeAndSearch(int websiteUserType, Pagination pagination, int support) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(websiteUserType);
		String str="";
		if(support ==-1){
			if(pagination.getSearch()!=null&&pagination.getSearch()!=""){
				str = "%"+pagination.getSearch()+"%";
				userRegister.getSqlMap().put("keyword", str);
			}
		}else{
			userRegister.getSqlMap().put("support",support);
			str = pagination.getSearch();
			userRegister.getSqlMap().put("keyword", str);
		}
		if(pagination.getIbId()>= 0){
			userRegister.getSqlMap().put("ibId", pagination.getIbId());
			if(pagination.getIbId() == 0 || pagination.getIbId() == UserUtil.getCurrAdmin().getUserId()) {
				userRegister.getSqlMap().put("flagIbid", 1);
			}
		}
		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			userRegister.getSqlMap().put("ibId",pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
		} else{
			userRegister.getSqlMap().put("ibId", pagination.getIbId());
			userRegister.getSqlMap().put("flagIbid", 1);
		}

		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		int totalCount = userRegisterDao.getUserRegisterCount(userRegister);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		userRegister.getSqlMap().put("offset", offset);
		userRegister.getSqlMap().put("limit", pageSize);
		List<UserRegister> userRegisterList= userRegisterDao.getUserRegister(userRegister);
		PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userRegisterList);
		return page;
	}
	@Override
	public PageIterator<UserRegister> pageQueryDemoByCondition(int pageNo,
			int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		int totalCount = userRegisterDao.queryCountDemoByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserRegister> userList = userRegisterDao.pageQueryDemoByCondition(params);
		PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public int queryRegEmailRepeat(String email, int dataSourceType) {
		return userRegisterDao.queryRegEmailRepeat(email, dataSourceType);
	}

	@Override
	public int queryRegenNameRepeat(String enName) {
		return userRegisterDao.queryRegenNameRepeat(enName);
	}

	@Override
	public List<UserRegister> getByIBId(Integer id) {
		return userRegisterDao.getByIBId(id);
	}

	@Override
	public List<UserRegister> getURByName(Map<String, Object> param) {
		return userRegisterDao.getURByName(param);
	}

	@Override
	public List<Integer> findUserIdByLevelPathisOne(Map<String, Object> param) {
		return userRegisterDao.findUserIdByLevelPathisOne(param);
	}

	@Override
	public List<UserRegister> findUserIdBySqlmapConditions(Map<String, Object> params) {
		return userRegisterDao.findUserIdBySqlmapConditions(params);
	}
	@Override
	public List<Integer> findUserRegisterByCname(Map<String, Object> params) {
		return userRegisterDao.findUserRegisterByCname(params);
	}
	@Override
	public UserRegister getUserByIbNameAndAgentName(String ibName, String agentName) {
		Map<String, String> params = Maps.newHashMap();
		params.put("ibName", ibName);
		params.put("agentName", agentName);
		List<UserRegister> list = userRegisterDao.getUserByIbNameAndAgentName(params);
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

	@Override
	public List<UserRegister> getSalesManagers(Integer id) {
		List<UserRegister> mngList = Lists.newArrayList();
		UserRegister currUser = userRegisterDao.getById(id, null);
		mngList.add(currUser);
		while (currUser.getSuperior_id() != 0) {
			currUser = userRegisterDao.getById(currUser.getSuperior_id(), null);
			if (currUser != null) {
				mngList.add(currUser);
			}
		}
		Collections.reverse(mngList);
		return mngList;
	}

	@Override
	public List<UserRegister> getSalesCustomers(Integer id) {
		UserRegister userRegister = new UserRegister();
		userRegister.setId(id);
		userRegister.setWebsiteUserType(UserAccountTypeEnum.PERSONAL_ACCOUNT.getValue());
		return userRegisterDao.findCustomersByUpper(userRegister);
	}

    @Override
    public List<UserRegister> getAllSonUsersById(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return userRegisterDao.findList("getAllSonUsersById", params);
    }

	@Override
	public List<UserRegister> getByName(HashMap<String, Object> params) {
		return userRegisterDao.findList("getByName", params);
	}

	@Override
    public List<UserRegister> getAllAdminUsers() {
        Map<String, Object> params = new HashMap<>();
        return userRegisterDao.findList("getAllAdminUsers", params);
    }

    @Override
	public PageIterator<UserRegister> pageQueryByCondition(int countryCode, int status, Pagination pagination,String dataRule ,String menuId,int support){
        Map<String, Object> params = new HashMap<String, Object>();
		UserRegister userRegister = (UserRegister) pagination.getEntity();
        if(menuId!=null){
            //params.put("menuId", menuId);
			userRegister.getSqlMap().put("menuId", menuId);
        }
        if(status > 0){
            //params.put("status", status);
			userRegister.getSqlMap().put("status", status);
        }
        if(dataRule!=null&&dataRule.length()>0){
            //params.put(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY, dataRule);
			userRegister.getSqlMap().put(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY, dataRule);
        }

		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			userRegister.getSqlMap().put("ibId", pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
			int ibId = pagination.getIbId();
			userRegister.getSqlMap().put("ibId",ibId);
		} else{
			int ibId = pagination.getIbId();

			userRegister.getSqlMap().put("ibId", ibId);
			userRegister.getSqlMap().put("flagIbid", 1);
		}
		if(!pagination.getQueryConfig().equals("0")){
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
			if(map.containsKey("agentId_QUERY_EQUALS")){
				//params.put("ibId", map.get("agentId_QUERY_EQUALS"));
				userRegister.getSqlMap().put("agentId", map.get("agentId_QUERY_EQUALS"));
			}
		}

        int dataSource = pagination.getDataSource();

        if (dataSource > 0) {
            //params.put("dataSource", dataSource);
			userRegister.getSqlMap().put("dataSource", dataSource);
        }
		String startTime="";
		String endTime="";
		if(pagination.getQueryConfig().equals("0")){
			if(pagination.getCustCaId()!=null && pagination.getCustCaId()!=0){
				//params.put("custcateId", pagination.getCustCaId());
				userRegister.getSqlMap().put("custcateId", pagination.getCustCaId());
			}
			Integer returnTime = pagination.getReturnTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			if(returnTime!=null){
				//params.put("returnTime", returnTime);
				userRegister.getSqlMap().put("returnTime", returnTime);
				if(returnTime==1){
					startTime = sdf.format(date)+" 00:00:00";
					endTime = sdf.format(date)+" 23:59:59";
					//params.put("startTime", startTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==2){
					startTime = sdf.format(date)+" 00:00:00";
					endTime = sdf.format(date)+" 00:00:00";
					//params.put("startTime", startTime);

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());


					userRegister.getSqlMap().put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==3){
					Date date2 = DateUtils.addDays(date,1);
					startTime = sdf.format(date2)+" 00:00:00";
					endTime = sdf.format(date2)+" 00:00:00";

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==4){
					Date date2 = DateUtils.addDays(date,1);
					startTime = sdf.format(date2)+" 00:00:00";
					endTime = sdf.format(date2)+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);

				} else if(returnTime==5){
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
					startTime = sdf2.format(date)+"-01 00:00:00";
					endTime = sdf.format(calendar.getTime())+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==-6){
					startTime = pagination.getStartData()+" 00:00:00";
					endTime = pagination.getEndData()+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				}
			}
		} else {
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
			if(map.containsKey("custcateId_QUERY_EQUALS")){
				//params.put("custcateId", map.get("custcateId_QUERY_EQUALS"));
				userRegister.getSqlMap().put("custcateId",  map.get("custcateId_QUERY_EQUALS"));
			}
			if(map.containsKey("custcateId_QUERY_NOTEQUALS")){
				//userRegister.getSqlMap().put("notcustcateId", map.get("custcateId_QUERY_NOTEQUALS"));
				userRegister.getSqlMap().put("notcustcateId",  map.get("custcateId_QUERY_NOTEQUALS"));
			}
			if(map.containsKey("salestime_QUERY_EQUALS")){
				userRegister.getSqlMap().put("salestime_QUERY_EQUALS", map.get("salestime_QUERY_EQUALS"));
				startTime = map.get("salestime_QUERY_EQUALS")+" 00:00:00";
				endTime = map.get("salestime_QUERY_EQUALS")+" 23:59:59";
				//params.put("startTime", startTime);
				//params.put("endTime", endTime);
				userRegister.getSqlMap().put("startTime", startTime);
				userRegister.getSqlMap().put("endTime", endTime);
			}
			if(map.containsKey("salestime_QUERY_MORE")){
				userRegister.getSqlMap().put("salestime_QUERY_MORE", map.get("salestime_QUERY_MORE"));
				startTime = map.get("salestime_QUERY_MORE")+" 23:59:59";
				//params.put("startTime", startTime);
				userRegister.getSqlMap().put("startTime", startTime);
			}
			if(map.containsKey("salestime_QUERY_LESS")){
				userRegister.getSqlMap().put("salestime_QUERY_LESS", map.get("salestime_QUERY_LESS"));
				startTime = map.get("salestime_QUERY_LESS")+" 00:00:00";
				//params.put("startTime", startTime);
				userRegister.getSqlMap().put("startTime", startTime);
			}
		}
		if(pagination.getLead_status()!=null && !"0".equals(pagination.getLead_status())){
			userRegister.getSqlMap().put("lead_status",pagination.getLead_status());
		}
		if(pagination.getInterest()!=null && !"0".equals(pagination.getInterest())){
			userRegister.getSqlMap().put("interest",pagination.getInterest());
		}
		if(pagination.getOrgId()!=null && !"".equals(pagination.getOrgId())){
			String orgId = pagination.getOrgId();
			String org[] = orgId.split(",");
			String orgIds = "";
			for (int i=0;i < org.length;i++){
				Organization organization = organizationService.findById(org[i]);
				List<Organization> parentid = organizationService.findByOrgId(organization);
				for(Organization s : parentid){
					orgIds = orgIds +s.getId()+",";
				}
				orgIds = orgIds+org[i]+",";
			}
			if(orgIds.length()>0){
				orgIds = orgIds.substring(0,orgIds.length()-1);
				userRegister.getSqlMap().put("orgId",orgIds);
			}else{
				userRegister.getSqlMap().put("orgId","-1");
			}
		}
		String agentusername = pagination.getAgentuserQuery();
		List<UserRegister> userRegisterList = new ArrayList<>();
        String username = pagination.getSearch();
		if(support ==-1){
			if (StringUtils.isNotBlank(username)) {
				username = "%" + username + "%";
				userRegister.getSqlMap().put("enName", username);
			}
			if(StringUtils.isNotBlank(agentusername)){
				agentusername = "%" +agentusername +"%";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("agentusername",agentusername);
				userRegisterList = userRegisterDao.getURByName(param);
				if(userRegisterList != null && userRegisterList.size()>0){
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}else{
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}
			}

		}else{
			userRegister.getSqlMap().put("support",support);
			if (StringUtils.isNotBlank(username)) {
				userRegister.getSqlMap().put("enName", username);
			}
			if(StringUtils.isNotBlank(agentusername)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("agentusername",agentusername);
				userRegisterList = userRegisterDao.getURByName(param);
				if(userRegisterList != null && userRegisterList.size()>0){
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}else{
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}
			}

		}



		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
        //后台分页参数
        int totalCount = userRegisterDao.queryCountByCondition(userRegister);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        //params.put("offset", offset);
		userRegister.getSqlMap().put("offset", offset);
        //params.put("limit", pageSize);
		userRegister.getSqlMap().put("limit", pageSize);
		//PageHelper.startPage((pagination.getOffset() / pagination.getLimit() + 1), pagination.getLimit());
        List<UserRegister> userList = userRegisterDao.queryByCondition(userRegister);
        PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(userList);
        return page;
	}

	@Override
	public void deleteById(UserRegister userRegister) {
		userRegisterDao.deleteById(userRegister);
	}

    @Override
    public List<UserRegister> getAllUsers() {
        return userRegisterDao.getAllUsers();
    }

    @Override
    public PageIterator<UserRegister> pageIbUserQueryByCondition(Pagination pagination) {
        Map<String, Object> params = new HashMap<>();
        String username = pagination.getSearch();
        if (StringUtils.isNotBlank(username)) {
            username = "%" + username + "%";
            params.put("enName", username);
        }
        // 后台分页参数
        int totalCount = userRegisterDao.queryCountIbByCondition(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserRegister> userRegisterList = userRegisterDao.pageQueryIbByCondition(params);
        PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(userRegisterList);
        return page;
    }

	@Override
	public void modifyIbUserTypeById(int id, int ibUserType) {
		userRegisterDao.modifyIbUserTypeById(id,ibUserType);
	}

    @Override
    public UserRegister selectUserRegister(int id) {
        return userRegisterDao.selectUserRegister(id);
    }

	@Override
	public int selectCountByIsIbId(int isIbId) {
		return userRegisterDao.selectCountByIsIbId(isIbId);
	}

	@Override
	public List<Integer> getAllibidsByUpperIB(Integer userId,
			int mt4DatasourceType) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("mt4DatasourceType", mt4DatasourceType);
        return userRegisterDao.findList("getAllibidsByUpperIB", params);
	}

	@Override
	public List<UserRegister> getUserRegisterByIbId(int userId) {
		return userRegisterDao.findList("getUserRegisterByIbId", userId);
	}

	@Override
	public UserRegister getLiveByEmailAndMt4DataSourceType(String userName, Integer mt4dataSourceType) {
		return userRegisterDao.getLiveByEmailAndMt4DataSourceType(userName, mt4dataSourceType);
	}

	@Override
	public List<UserRegister> getAllSales() {
		return userRegisterDao.getAllSales();
	}

	@Override
	public List<UserRegister> getParentUsersByRoleIdAndDataSourceId(int roleId, int dataSourceId) {
		return userRegisterDao.getParentUsersByRoleIdAndDataSourceId(roleId, dataSourceId);
	}

	@Override
	public List<UserRegister> getUpperIBAndSalesByDataSource(int dataSourceId) {
		return userRegisterDao.getUpperIBAndSalesByDataSource(dataSourceId);
	}

    @Override
    public List<UserRegister> getIBUsersByIbId(int ibId) {
        return userRegisterDao.getIBUsersByIbId(ibId);
    }

	@Override
	public UserRegister getById(Integer uid, Integer dataSourceId) {
		return userRegisterDao.getById(uid, dataSourceId);
	}

    @Override
    public List<UserRegister> getAllIBUserByDataSource(int dataSource) {
        return userRegisterDao.getAllIBUserByDataSource(dataSource);
    }

	@Override
	public List<UserRegister> findUsersByOrgId(Integer orgId) {
		UserRegister user = new UserRegister();
		user.setOrgId(orgId);
		user.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u1"));
		return userRegisterDao.findUsersByOrgId(user);
	}

	@Override
	public List<UserRegister> getIBByParentId(Integer id, Integer datasource) {
		UserRegister user = new UserRegister();
		user.setIs_ibId(id);
		user.setMt4DataSourceId(datasource);
		user.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userRegisterDao.getIBByParentId(user);
	}

    @Override
    public UserRegister getLiveByEmailAndMt4DataSourceId(String email, int mt4dataSourceId) {
        return userRegisterDao.getLiveByEmailAndMt4DataSourceId(email, mt4dataSourceId);
    }

	@Override
	public List<UserRegister> getUserRegisterByStatus(HashMap<String, Object> params) {
		return userRegisterDao.findList("getUserRegisterByStatus", params);
	}

	@Override
	public List<UserRegister> getByWebsiteUserTypess(int value) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("websiteUserType", value);
		return userRegisterDao.findList("getByWebsiteUserTypess", params);
	}

    @Override
    public List<UserRegister> customersExportDownload(int countryCode, int status, Pagination pagination,String dataRule ,String menuId,int support) {
		Map<String, Object> params = new HashMap<String, Object>();
		UserRegister userRegister = (UserRegister) pagination.getEntity();
		if(menuId!=null){
			//params.put("menuId", menuId);
			userRegister.getSqlMap().put("menuId", menuId);
		}
		if(status > 0){
			//params.put("status", status);
			userRegister.getSqlMap().put("status", status);
		}
		if(dataRule!=null&&dataRule.length()>0){
			//params.put(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY, dataRule);
			userRegister.getSqlMap().put(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY, dataRule);
		}


		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			userRegister.getSqlMap().put("ibId", pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
		} else{
			int ibId = pagination.getIbId();

			userRegister.getSqlMap().put("ibId", ibId);
			userRegister.getSqlMap().put("flagIbid", 1);
		}
		if(!pagination.getQueryConfig().equals("0")){
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
			if(map.containsKey("agentId_QUERY_EQUALS")){
				//params.put("ibId", map.get("agentId_QUERY_EQUALS"));
				userRegister.getSqlMap().put("agentId", map.get("agentId_QUERY_EQUALS"));
			}
		}
		int dataSource = pagination.getDataSource();

		if (dataSource > 0) {
			//params.put("dataSource", dataSource);
			userRegister.getSqlMap().put("dataSource", dataSource);
		}
		String startTime="";
		String endTime="";
		if(pagination.getQueryConfig().equals("0")){
			if(pagination.getCustCaId()!=null && pagination.getCustCaId()!=0){
				//params.put("custcateId", pagination.getCustCaId());
				userRegister.getSqlMap().put("custcateId", pagination.getCustCaId());
			}
			Integer returnTime = pagination.getReturnTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
			Date date = new Date();
			if(returnTime!=null){
				//params.put("returnTime", returnTime);
				userRegister.getSqlMap().put("returnTime", returnTime);
				if(returnTime==1){
					startTime = sdf.format(date)+" 00:00:00";
					endTime = sdf.format(date)+" 23:59:59";
					//params.put("startTime", startTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==2){
					startTime = sdf.format(date)+" 00:00:00";
					endTime = sdf.format(date)+" 00:00:00";
					//params.put("startTime", startTime);

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());


					userRegister.getSqlMap().put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==3){
					Date date2 = DateUtils.addDays(date,1);
					startTime = sdf.format(date2)+" 00:00:00";
					endTime = sdf.format(date2)+" 00:00:00";

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==4){
					Date date2 = DateUtils.addDays(date,1);
					startTime = sdf.format(date2)+" 00:00:00";
					endTime = sdf.format(date2)+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);

					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);

				} else if(returnTime==5){
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
					startTime = sdf2.format(date)+"-01 00:00:00";
					endTime = sdf.format(calendar.getTime())+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				} else if(returnTime==-6){
					startTime = pagination.getStartData()+" 00:00:00";
					endTime = pagination.getEndData()+" 23:59:59";
					//params.put("startTime", startTime);
					//params.put("endTime", endTime);
					startTime =   DateUtil.dateTransformBetweenTimeZone(startTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());
					endTime =   DateUtil.dateTransformBetweenTimeZone(endTime,DateUtil.DATE_FORMAT, UserUtil.getUserUTC(), DateUtil.getCurrentTimeZone());

					userRegister.getSqlMap().put("startTime", startTime);
					userRegister.getSqlMap().put("endTime", endTime);
				}
			}
		} else {
			Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
			Map<String,String> map = new LinkedHashMap<String,String>();
			map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
			if(map.containsKey("custcateId_QUERY_EQUALS")){
				//params.put("custcateId", map.get("custcateId_QUERY_EQUALS"));
				userRegister.getSqlMap().put("custcateId",  map.get("custcateId_QUERY_EQUALS"));
			}
			if(map.containsKey("custcateId_QUERY_NOTEQUALS")){
				//userRegister.getSqlMap().put("notcustcateId", map.get("custcateId_QUERY_NOTEQUALS"));
				userRegister.getSqlMap().put("notcustcateId",  map.get("custcateId_QUERY_NOTEQUALS"));
			}
			if(map.containsKey("salestime_QUERY_EQUALS")){
				userRegister.getSqlMap().put("salestime_QUERY_EQUALS", map.get("salestime_QUERY_EQUALS"));
				startTime = map.get("salestime_QUERY_EQUALS")+" 00:00:00";
				endTime = map.get("salestime_QUERY_EQUALS")+" 23:59:59";
				//params.put("startTime", startTime);
				//params.put("endTime", endTime);
				userRegister.getSqlMap().put("startTime", startTime);
				userRegister.getSqlMap().put("endTime", endTime);
			}
			if(map.containsKey("salestime_QUERY_MORE")){
				userRegister.getSqlMap().put("salestime_QUERY_MORE", map.get("salestime_QUERY_MORE"));
				startTime = map.get("salestime_QUERY_MORE")+" 23:59:59";
				//params.put("startTime", startTime);
				userRegister.getSqlMap().put("startTime", startTime);
			}
			if(map.containsKey("salestime_QUERY_LESS")){
				userRegister.getSqlMap().put("salestime_QUERY_LESS", map.get("salestime_QUERY_LESS"));
				startTime = map.get("salestime_QUERY_LESS")+" 00:00:00";
				//params.put("startTime", startTime);
				userRegister.getSqlMap().put("startTime", startTime);
			}
		}
		if(pagination.getLead_status()!=null && !"0".equals(pagination.getLead_status())){
			userRegister.getSqlMap().put("lead_status",pagination.getLead_status());
		}
		if(pagination.getInterest()!=null && !"0".equals(pagination.getInterest())){
			userRegister.getSqlMap().put("interest",pagination.getInterest());
		}
		if(pagination.getOrgId()!=null && !"".equals(pagination.getOrgId())){
			String orgId = pagination.getOrgId();
			String org[] = orgId.split(",");
			String orgIds = "";
			for (int i=0;i < org.length;i++){
				Organization organization = organizationService.findById(org[i]);
				List<Organization> parentid = organizationService.findByOrgId(organization);
				for(Organization s : parentid){
					orgIds = orgIds +s.getId()+",";
				}
				orgIds = orgIds+org[i]+",";
			}
			if(orgIds.length()>0){
				orgIds = orgIds.substring(0,orgIds.length()-1);
				userRegister.getSqlMap().put("orgId",orgIds);
			}else{
				userRegister.getSqlMap().put("orgId","-1");
			}
		}
		String agentusername = pagination.getAgentuserQuery();
		List<UserRegister> userRegisterList = new ArrayList<>();
		String username = pagination.getSearch();
		if(support ==-1){
			if (StringUtils.isNotBlank(username)) {
				username = "%" + username + "%";
				userRegister.getSqlMap().put("enName", username);
			}
			if(StringUtils.isNotBlank(agentusername)){
				agentusername = "%" +agentusername +"%";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("agentusername",agentusername);
				userRegisterList = userRegisterDao.getURByName(param);
				if(userRegisterList != null && userRegisterList.size()>0){
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}else{
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}
			}

		}else{
			userRegister.getSqlMap().put("support",support);
			if (StringUtils.isNotBlank(username)) {
				userRegister.getSqlMap().put("enName", username);
			}
			if(StringUtils.isNotBlank(agentusername)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("agentusername",agentusername);
				userRegisterList = userRegisterDao.getURByName(param);
				if(userRegisterList != null && userRegisterList.size()>0){
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}else{
					userRegister.getSqlMap().put("agentUserList",userRegisterList);
				}
			}

		}
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
        List<UserRegister> userList = userRegisterDao.customersExportDownload(userRegister);
        return userList;
    }

	@Override
	public List<UserRegister> findNoIb() {
		return userRegisterDao.findNoIb();
	}



    @Override
    public Pagination getExperienceAct(Pagination pagination) {
        UserRegister userRegister = new UserRegister();
        String search = pagination.getSearch();
        if (StringUtils.isNotBlank(search) && StringUtils.isNotEmpty(search)) {
            userRegister.getSqlMap().put("enName", search);
        }
        userRegister.getSqlMap().put("ibId", pagination.getIbId());

		if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
			userRegister.getSqlMap().put("ibId", pagination.getIbId_all());
		} else if(pagination.getIbId()== -1) {
			int ibId = pagination.getIbId();
			userRegister.getSqlMap().put("ibId",ibId);
		} else{
			int ibId = pagination.getIbId();

			userRegister.getSqlMap().put("ibId", ibId);
			userRegister.getSqlMap().put("flagIbid", 1);
		}

        userRegister.getSqlMap().put("dataSource", pagination.getDataSource());
        userRegister.getSqlMap().put("limit", pagination.getLimit());
		userRegister.getSqlMap().put("offset", pagination.getOffset());
        userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
        // 后台分页参数
        int totalCount = userRegisterDao.getExperienceActCount(userRegister);
        List<UserRegister> userRegisterList = userRegisterDao.getExperienceAct(userRegister);

        for (UserRegister register : userRegisterList) {
			String userAc = "";
            UserRegister parentUserRegister = super.findById(register.getIs_ibId());
            if (null != parentUserRegister) {
                register.setAgentName(parentUserRegister.getCnName());
            }
			List<UserMT4Account> userMT4AccountList = null;
			try {
				userMT4AccountList = userMT4AccountService.getUserMT4AccountByUid(register.getId());
			} catch (PayException e) {
				logger.error("查询Mt4账号报错", e.getMessage());
			}
			for (UserMT4Account userMT4Account : userMT4AccountList) {
				userAc = userAc + "," + userMT4Account.getMt4Account();
			}
			if (userAc != "" && userAc.length() > 0) {
				userAc = userAc.substring(1, userAc.length());//将字符串最后的 ‘，’去掉
			}
			register.setUserAccount(userAc);
        }

        pagination.setTotal(totalCount);
        pagination.setRows(userRegisterList);
        return pagination;
    }

	//循环查找上级
	@Override
	public List<UserRegister> getAllSuperiors(Integer id , Integer dataSourceId) {
		List<UserRegister> userRegistersList = new ArrayList<UserRegister>();
		UserRegister userRegister = userRegisterDao.selectById(id);
		if (userRegister.getWebsiteUserType() == 2) {
			userRegister.setRoleName("Personal Account");
		}
		if(userRegister!=null){
			userRegistersList.add(userRegister);

			while(userRegister.getIs_ibId()!=0){   //1是root
				userRegister = userRegisterDao.selectById(userRegister.getIs_ibId());
				if(userRegister!=null){
					userRegistersList.add(userRegister);
				} else {
					break;
				}

				if(userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
					break;
				}
			}
		}
		for(UserRegister userRegister2:userRegistersList){
			if(userRegister2.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
				Admin admin = adminService.findByUserId(userRegister2.getId());
				if(admin!=null){
					Role role = roleService.findById(admin.getRoleId());
					if(role!=null){
						userRegister2.setRoleName(role.getName());
					}
				}
			} else if(userRegister2.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
				User user = userService.getByUid(userRegister2.getId());
				if(user!=null){
					Role role = roleService.findById(user.getRoleId());
					if(role!=null){
						userRegister2.setRoleName(role.getName());
					}
				}
			}
		}
		List<UserRegister> userRegisterList2 = new ArrayList<>();
		for(int i=userRegistersList.size()-1;i>=0;i--){
			userRegisterList2.add(userRegistersList.get(i));
		}
		return userRegisterList2;
	}

	@Override
	public List<UserRegister> getAllSuperiorsByaccount(Integer account, Integer dataSourceId) {
		List<UserRegister> userRegistersList = new ArrayList<UserRegister>();
		List<UserRegister> userRegisterList2 = new ArrayList<>();
		UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Account(account);
		if(userMT4Account!=null){
			UserRegister userRegister = userRegisterDao.selectById(userMT4Account.getUid());
			if (userRegister.getWebsiteUserType() == 2) {
				userRegister.setRoleName("Personal Account");
			}
			userRegistersList.add(userRegister);
			if(userMT4Account.getIbId()!=0){
				userRegister = userRegisterDao.selectById(userMT4Account.getIbId());
				if(userRegister!=null){
					userRegistersList.add(userRegister);

					while(userRegister.getIs_ibId()!=0){   //1是root
						userRegister = userRegisterDao.selectById(userRegister.getIs_ibId());
						if(userRegister!=null){
							userRegistersList.add(userRegister);
						} else {
							break;
						}

						if(userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
							break;
						}
					}
				}
			}
			for(UserRegister userRegister2:userRegistersList){
				if(userRegister2.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
					Admin admin = adminService.findByUserId(userRegister2.getId());
					if(admin!=null){
						Role role = roleService.findById(admin.getRoleId());
						if(role!=null){
							userRegister2.setRoleName(role.getName());
						}
					}
				} else if(userRegister2.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
					User user = userService.getByUid(userRegister2.getId());
					if(user!=null){
						Role role = roleService.findById(user.getRoleId());
						if(role!=null){
							userRegister2.setRoleName(role.getName());
						}
					}
				}
			}
			for(int i=userRegistersList.size()-1;i>=0;i--){
				userRegisterList2.add(userRegistersList.get(i));
			}
		}

		return userRegisterList2;
	}

	@Override
	public HashMap<Integer, UserRegister> getbyUids(List<GiftsUser> giftsUserList) {
		List<Integer> uids = new ArrayList<>();
		for (GiftsUser giftsUser : giftsUserList){
			uids.add(giftsUser.getUid());
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (giftsUserList != null && giftsUserList.size()>0){
			params.put("uids", uids);
		}
		List<UserRegister> userRegisterList = userRegisterDao.getbyUids(params);
		HashMap<Integer, UserRegister> map = new HashMap<Integer, UserRegister>();
		if (userRegisterList != null && userRegisterList.size() > 0){
			for (UserRegister userRegister : userRegisterList){
				map.put(userRegister.getId(),userRegister);
			}
		}
		return map;
	}

	@Override
	public List<UserRegister> selectIBAndDemoAndPersonal(Map parms) {
		return userRegisterDao.selectIBAndDemoAndPersonal(parms);
	}

	@Override
	public List<Integer> findUidsByCondition(HashMap<String, Object> params) {
		UserRegister userRegister = new UserRegister();
		userRegister.getSqlMap().put("params",params);
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userRegisterDao.findUidsByCondition(userRegister);
	}

	@Override
	public List<Integer> findUidsByConditionTrader(HashMap<String, Object> params) {
		UserRegister userRegister = new UserRegister();
		userRegister.getSqlMap().put("params",params);
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		return userRegisterDao.findUidsByConditionTrader(userRegister);
	}


	@Override
	public List<UserRegister> getUserRegisterByName(String userName) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", userName);
		return userRegisterDao.getUserRegisterByName(params);
	}


	@Override
	public List<UserRegister> selectSalesRepeat(Map parms) {
		return userRegisterDao.selectSalesRepeat(parms);
	}

    @Override
    public List<UserRegister> getAllExpUsers() {
        return userRegisterDao.getAllExpUsers();
    }

	@Override
	public List<UserRegister> getUserRegisterBySuperId(Integer superiorId) {

		return userRegisterDao.findList("getUserRegisterBySuperId", superiorId);
	}

	@Override
	public List<UserRegister> getUidsByCondition(int ibId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ibId", ibId);
		return userRegisterDao.getUidsByCondition(params);
	}

	@Override
	public List<UserRegister> getByNameId(HashMap<String, Object> params) {
		return userRegisterDao.getByNameId(params);
	}

	@Override
	public UserRegister geiInnerUserByEmail(String email) {
		return (UserRegister) userRegisterDao.findOne("geiInnerUserByEmail", email);
	}

	@Override
	public PageIterator<UserRegister> getByWebsiteUserTypeAndLikeName(
			int value, Pagination pagination,HashMap<String,Object> params) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(value);

		userRegister.getSqlMap().put("params", params);
		if(null!=(String)params.get("login")){
			userRegister.getSqlMap().put("name", params.get("login"));
		}
		if(null!=(Integer)params.get("status")){
			userRegister.getSqlMap().put("status", params.get("status"));
		}

		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
		userRegister.getSqlMap().put("sqldsfu2", dataScopeFilter("org", "u2"));
		int totalCount = userRegisterDao.getUserRegisterCountBystus(userRegister);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		userRegister.getSqlMap().put("offset", offset);
		userRegister.getSqlMap().put("limit", pageSize);
		List<UserRegister> userRegisterList= userRegisterDao.getUserRegisterStatus(userRegister);
		PageIterator<UserRegister> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userRegisterList);
		return page;
	}

	@Override
	public List<UserRegister> getUserRegisterByLikeName(String login, int value) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("name", "%"+login+"%");
		params.put("websiteUserType", value);
		return userRegisterDao.findList("getUserRegisterByLikeName", params);
	}

	@Override
	public List<UserRegister> getByWebsiteUserTypeAndLikeNameNoPage(int value,
			HashMap<String, Object> params) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(value);

		if(null!=(String)params.get("login")){
			userRegister.getSqlMap().put("name", params.get("login"));
		}
		if(null!=(Integer)params.get("status")){
			userRegister.getSqlMap().put("status", params.get("status"));
		}

		userRegister.getSqlMap().put("params", params);
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
		return userRegisterDao.findList("getByWebsiteUserTypeAndLikeNameNoPage", userRegister);
	}

	@Override
	public List<UserRegister> getInnerUserByOrgId(Integer orgId, String name) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(6);
		userRegister.setOrgId(orgId);
		if(name != null && name.trim() != "") {
			userRegister.setCnName("%"+name+"%");
		}
		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
		return userRegisterDao.findUserByEntity(userRegister);
	}

	@Override
	public List<UserRegister> getIBUserByParentId(String userId, Integer orgId, String name) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(5);
		userRegister.setIs_ibId(Integer.valueOf(userId));
		userRegister.setOrgId(-1);
		if(name != null && name.trim() != "") {
			userRegister.setCnName("%"+name+"%");
		}
//		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
		return userRegisterDao.findUserByEntity(userRegister);
	}

	@Override
	public List<UserRegister> getInnerUserByParentId(String queryId, Integer orgId, String name) {
		UserRegister userRegister = new UserRegister();
		userRegister.setWebsiteUserType(6);
		userRegister.setSuperior_id(Integer.valueOf(queryId));
		userRegister.setOrgId(orgId);
		if(name != null && name.trim() != "") {
			userRegister.setCnName("%"+name+"%");
		}
//		userRegister.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
		return userRegisterDao.findUserByEntity(userRegister);
	}

	@Override
	public List<UserRegister> getAllSonUsersBySuperId(int ibIds) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", ibIds);
		return userRegisterDao.findList("getAllSonUsersBySuperId", params);
	}

	@Override
	public List<UserRegister> getLeadsExist(UserRegister email) {
		return userRegisterDao.getLeadsExist(email);
	}

	@Override
	public void updateIsIbidByIdList(List<String> idArray, Integer agent) {
		UserRegister userRegister = new UserRegister();
		userRegister.getSqlMap().put("ids", idArray);
		userRegister.setIs_ibId(agent);
		userRegister.setUpdateUser("" + UserUtil.getCurrAdmin().getUserId());
		userRegister.setUpdateTime(DateUtil.getCurrentTime());
		userRegisterDao.doUpdate("updateIsIbidByIdList", userRegister);
	}

	@Override
	public Map<Integer,UserRegister> findbyUidAndDataSource(List<Integer> idList, Integer datasource) {

		Map<String,Object> param = new HashMap<>();
		if(idList != null && idList.size() >0) {
			param.put("idList",idList);
		}
		param.put("datasource",datasource);
		List<UserRegister> userRegisters =userRegisterDao.getByIdListAndDatasource(param);

		Map<Integer,UserRegister> maps = new HashMap<>();
		for (UserRegister userRegister : userRegisters) {
			if(!maps.containsKey(userRegister.getId())){
				maps.put(userRegister.getId(),userRegister);
			}
		}
		return maps;

	}

	/**
	 * 根据WebSiteUserType查询多个类型的客户信息
	 * @param idList webSiteUserType多条数据，用in查询
	 * @return
	 */
	@Override
	public List<UserRegister> getUserRegisterByWebSiteType(List<Integer> idList){
		if(null == idList || idList.size() <= 0){
			return null;
		}
		return userRegisterDao.getUserRegisterByWebSiteType(idList);
	}

	@Override
	public List<UserRegister> findUserregisterByIdList(List<String> idArray) {
		UserRegister userRegister = new UserRegister();
		userRegister.getSqlMap().put("ids", idArray);
		return userRegisterDao.findList("findUserregisterByIdList",userRegister);
	}

    @Override
    public List<UserRegister> findAllUsers() {
        Map<String, Object> params = new HashMap<>();
        return userRegisterDao.findList("findAllUsers", params);
    }

    @Override
    public List<UserRegister> getAllLivesAndAllIbs() {
        Map<String, Object> params = new HashMap<>();
        return userRegisterDao.findList("getAllLivesAndAllIbs", params);
    }

}