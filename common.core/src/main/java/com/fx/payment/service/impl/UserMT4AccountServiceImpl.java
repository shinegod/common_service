package com.fx.payment.service.impl;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4TradeRecordUtil;
import com.fx.MT4.vo.MT4TradeRecord;
import com.fx.enums.IsDelEnum;
import com.fx.mt4TradeRecord.dao.IMt4UsersDao;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.payment.dao.IUserMT4AccountDao;
import com.fx.payment.dao.impl.UserMT4AccountDaoImpl;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.task.model.TaskManage;
import com.fx.user.model.UserRegister;
import com.fx.util.Constants;
import com.fx.util.DateUtil;
import com.fx.util.Pagination;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserMT4AccountServiceImpl extends BaseVOService<UserMT4Account> implements IUserMT4AccountService {
    @Autowired
    private IUserMT4AccountDao userMT4AccountDao;
    
    @Autowired
    private IMt4UsersDao mt4UsersDao;
    
    

	@Override
	public List<UserMT4Account> getUserMT4AccountByUid(int uid,Integer flag) {
	  List<UserMT4Account> userMt4Account = userMT4AccountDao.getUserMT4AccountByUid(uid);
	  //TODO 添加获取账户余额信息
	  if(flag !=null){
		   if(userMt4Account !=null && !userMt4Account.isEmpty()){
			   List<Integer>logins = new ArrayList<Integer>();
			   for (UserMT4Account userMT4Account2 : userMt4Account) {
				        logins.add(userMT4Account2.getMt4Account());
			   }
			   List<Mt4Users>  mt4Users =   mt4UsersDao.getMt4UsersByLogin(logins);
			   for (Mt4Users mt4Users2 : mt4Users) {
				   //TODO 获取id组装对象
				   for (int i = 0; i < userMt4Account.size(); i++) {
					    if(mt4Users2.getLogin()== userMt4Account.get(i).getMt4Account()){
					    	if(mt4Users2.getBalance() == null){
					    		userMt4Account.get(i).setAccountBalance( new BigDecimal(0.0D));
					    		continue;
					    	}else{
					    		userMt4Account.get(i).setAccountBalance( new BigDecimal(mt4Users2.getBalance()));
					    		continue;
					    	}
					    }
				   }
			   }
			   
		   }
	  }
		
		return userMt4Account;
	}
	
	@Override
	public int getUserCommissionAccountByUid(Integer uid) {
		UserMT4Account userMT4Account=userMT4AccountDao.getUserCommissionAccountByUid(uid);
		if (userMT4Account==null) 
			return 0;
		else 
			return  userMT4Account.getMt4Account();
	}

	@Override
	public int createUserMT4Account(UserMT4Account userMT4Account)
			throws PayException {
		if(null == userMT4Account){
    		throw new PayException("-1:创建个人帐户时参数为空");
    	}
    	if(null == userMT4Account.getUid() || userMT4Account.getUid() <= 0){
    		throw new PayException("-2:创建个人帐户时，uid为空");
    	}
    	if(StringUtils.isBlank(userMT4Account.getCreateUser())){
    		throw new PayException("-3:操作者为空");
    	}
    	if(StringUtils.isBlank(userMT4Account.getCreateIp())){
    		throw new PayException("-4:操作者IP为空");
    	}
    	UserMT4Account ua = new UserMT4Account();
    	ua.setUid(userMT4Account.getUid());
    	ua.setMt4Account(userMT4Account.getMt4Account());
    	ua.setStatus(userMT4Account.getStatus());
    	ua.setCreateUser(userMT4Account.getCreateUser());
    	ua.setCreateTime(DateUtil.getCurrentTime());
    	ua.setCreateIp(userMT4Account.getCreateIp());
    	ua.setUpdateUser(userMT4Account.getUpdateUser());
    	ua.setUserStaus(userMT4Account.getUserStaus());
    	ua.setUpdateTime(DateUtil.getCurrentTime());
    	ua.setUserMT4AccountType(userMT4Account.getUserMT4AccountType());
    	ua.setUpdateIp(userMT4Account.getCreateIp());
    	ua.setIsDel(IsDelEnum.NO.getValue());
		ua.setIbId(userMT4Account.getIbId());
		ua.setDataSourceId(userMT4Account.getDataSourceId());
		ua.setAgentMt4Account(userMT4Account.getAgentMt4Account());
		ua.setNamesake(userMT4Account.getNamesake());
		ua.setReason(userMT4Account.getReason());
		ua.setAccountType(userMT4Account.getAccountType());
    	int result = userMT4AccountDao.doInsert("insert", ua);
    	logger.debug("######userAccountDao.doInsert");
    	if(result <= 0){
    		throw new PayException("-5:创建用户账户出错");
    	}
    	return result;
	}
	private String getTradeCode(int uid){
    	if(uid <= 0){
    		return null;
    	}
    	String zeroStr = "0000000000";
    	String idStr = String.valueOf(uid);
    	String tradeCode = new StringBuilder("KCM-").append(zeroStr.substring(0, 10 - idStr.length())).append(idStr).toString();
    	return tradeCode;
    }
	
	public static void main(String[] args) {
		IUserMT4AccountDao ss = new UserMT4AccountDaoImpl();
		ss.findById("", 359);
	}

	@Override
	public PageIterator<UserMT4Account> pageQueryByConditiontByStatus(int userMT4AccountType,int status,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > -1){
			params.put("status", status);
		}
		params.put("userMT4AccountType", userMT4AccountType);
		int totalCount = userMT4AccountDao.queryCountByConditionByStatus(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userList = userMT4AccountDao.queryByCondition(params);
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}

	@Override
	public PageIterator<UserMT4Account> pageQueryByConditiontByStatus(int userMT4AccountType,int status, Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > -1){
			params.put("status", status);
		}

		params.put("userMT4AccountType", userMT4AccountType);
		int totalCount = userMT4AccountDao.queryCountByConditionByStatus(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userMT4AccountList = userMT4AccountDao.queryByCondition(params);
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public UserMT4Account getByUidAndUserStaus(int uid,int userStatus) {
		return userMT4AccountDao.getByUidAndUserStaus(uid,userStatus);
	}

	@Override
	public int selectCountByStatus(int uid, int userStatus) {
		return userMT4AccountDao.selectCountByStatus(uid,userStatus);
	}

    @Override
    public List<UserMT4Account> selectMt4AccountByStatus(Integer userId, int status, int dataSource) {
        return userMT4AccountDao.selectMt4AccountByStatus(userId, status, dataSource);
    }
	@Override
	public List<UserMT4Account> selectMt4AccountByStatus2(Integer userId, int status, int dataSource,int mt4Account2) {
		return userMT4AccountDao.selectMt4AccountByStatus2(userId, status, dataSource,mt4Account2);
	}

    @Override
    public Integer deleteByUid(Integer userId) {
       return userMT4AccountDao.deleteByUid(userId);
    }

	@Override
	public int selectCountByIsIbId(int ibid) {
		return userMT4AccountDao.selectCountByIsIbId(ibid);
	}

	@Override
	public List<UserMT4Account> selectAllAndNoDel() {
		return userMT4AccountDao.selectAllAndNoDel();
	}

	@Override
	public void updautomatedReview(int id,int auto) {
		userMT4AccountDao.updautomatedReview(id, auto);
	}

	@Override
	public UserMT4Account getUserMT4AccountByMt4Account(int mt4Account) {
		return userMT4AccountDao.getUserMT4AccountByMt4Account(mt4Account);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUidStatus(int uid, int status)
			throws PayException {
		return userMT4AccountDao.getUserMT4AccountByUidStatus(uid,status);
	}
		// queryConditionMap contains IBid, group, queryFrom & queryTo
		
	@Override
	public List<MT4TradeRecord> queryOrderByTimeAndIB(Map<String, Object> queryConditionMap,
			List<MT4TradeRecord> orderList) {
		List<Integer> userList=userMT4AccountDao.queryByGroupAndIB(queryConditionMap);
		if (userList.size()==0) return orderList;
		String from=(String) queryConditionMap.get("queryFrom");
		String to=(String) queryConditionMap.get("queryTo");
		orderList=MT4TradeRecordUtil.getHistoryByGroup(new ArrayList<Integer>(userList), from, to, QueryMtT4GroupIdEnum.LIVE);
		
		return orderList;
	}

	@Override
	public UserMT4Account getUserMT4AccountByUidUserStatus(int uid,int userStatus) {
		return userMT4AccountDao.getUserMT4AccountByUidUserStatus(uid,userStatus);
	}
	
	@Override
	public List<UserMT4Account> getUserMT4AccountByUidCount(Integer userId) {
		return userMT4AccountDao.getUserMT4AccountByUidCount(userId);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByGroup(Integer groupId) {
		return userMT4AccountDao.getUserMT4AccountByGroup(groupId);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByGroupUidList(
			int queryGroupId, ArrayList<Integer> userIdList) {
		return userMT4AccountDao.getUserMT4AccountByGroupUidList(queryGroupId, userIdList);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByIbidListAndUid(
			Integer userId, List<Integer> ibidList, int mt4DatasourceType) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("ibidList", ibidList);
		params.put("mt4DatasourceType", mt4DatasourceType);
        return userMT4AccountDao.findList("getUserMT4AccountByIbidListAndUid", params);
	}

	@Override
	public UserMT4Account getUserMT4AccountByMt4Account(Integer login,
			int dataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("mt4Account", login);
		params.put("dataSourceId", dataSourceId);
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params", params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		 return (UserMT4Account) userMT4AccountDao.findOne("getUserMT4AccountByMt4AccountAndDataSource", userMT4Account);
	}
	@Override
	public UserMT4Account getUserMT4AccountByMt4Account2(Integer login,
														int dataSourceId,String mt4Account) {
		Map<String, Object> params = new HashMap<>();
		params.put("mt4Account", login);
		params.put("mt4Account2",mt4Account);
		params.put("dataSourceId", dataSourceId);
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params", params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		return (UserMT4Account) userMT4AccountDao.findOne("getUserMT4AccountByMt4AccountAndDataSource", userMT4Account);
	}
	@Override
	public List<UserMT4Account> getUserMT4AccountByIbId(int ib_id) {
		return userMT4AccountDao.getUserMT4AccountByIbId(ib_id);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUidAndStatus(Integer userId,
			int value) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("status", value);
        return userMT4AccountDao.findList("getUserMT4AccountByUidAndStatus", params);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountListByUid(Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", id);
		return userMT4AccountDao.findList("getUserMT4AccountByUidAndStatus", params);
	}

    @Override
    public UserMT4Account getBeanByMt4Account(int agentMt4Account, int dataSourceId) {
        UserMT4Account userMT4Account = new UserMT4Account();
        userMT4Account.getSqlMap().put("login", agentMt4Account);
        userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
        UserMT4Account userMt4Account = userMT4AccountDao.getBeanByMt4Account(userMT4Account);
        return userMt4Account;
    }


    @Override
	public PageIterator<UserMT4Account> pageQueryByConditiontByUid(
		List<Integer> uidList,int userid, Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uidList", uidList);
		params.put("agentMt4Account", userid);
		int totalCount = userMT4AccountDao.queryCountByConditionByUid(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userMT4AccountList = userMT4AccountDao.queryByConditionByUid(params);
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public UserMT4Account getUserCommissionUserMt4AccountByUid(Integer uid) {
		return userMT4AccountDao.getUserCommissionAccountByUid(uid);
	}

    @Override
    public List<UserMT4Account> getUserMT4AccountByDataSourceId(int dataSourceId) {
        return userMT4AccountDao.getUserMT4AccountByDataSourceId(dataSourceId);
    }

    @Override
	public PageIterator<UserMT4Account> pageQueryUserMT4AccountByUidCount(
			Integer userId, int dataSourceId, Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("dataSourceId", dataSourceId);
		int totalCount = userMT4AccountDao.pageCountUserMT4AccountByUidCount(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryUserMT4AccountByUidCount(params);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < userMT4AccountList.size(); i++) {
			try {
				String day = format.format(format.parse(userMT4AccountList.get(i).getUpdateTime()));
				userMT4AccountList.get(i).setUpdateTime(day);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public List<UserMT4Account> getUserCommissionAccountsByUidAndDataSourceId(
			Integer id, int mt4DataSourceId) {
		return userMT4AccountDao.getUserCommissionAccountsByUidAndDataSourceId(id, mt4DataSourceId);
	}
	
	@Override
	public List<UserMT4Account> getUserMT4AccountByUserUserStatus(Integer id,
			int value) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("uid", id);
		params.put("userStatus", value);
		return userMT4AccountDao.findList("getUserMT4AccountByUserUserStatus", params);
	}
	
	@Override
	public PageIterator<UserMT4Account> pageQueryCommissionByUid(Integer userId,Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userId);
		int totalCount = userMT4AccountDao.queryCountCommissionByUid(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryCommissionByUid(params);
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public PageIterator<UserMT4Account> pageQueryByConditiontByIbidAndAgentAccount(
			Integer userId, int parseInt, Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ibId", userId);
		params.put("agentAccount", parseInt);
		int totalCount = userMT4AccountDao.queryCountByConditiontByIbidAndAgentAccount(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryByConditiontByIbidAndAgentAccount(params);
		PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}

	@Override
	public UserMT4Account getByMt4Account(int login) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("login",login);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		UserMT4Account userMt4Account = userMT4AccountDao.getByMt4Account(userMT4Account);
		return userMt4Account;
	}

	@Override
	public List<Integer> findLoginsByConditionTrader(HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findLoginsByConditionTrader(userMT4Account);
	}

	@Override
	public List<Integer> findLoginsByCondition(HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findLoginsByCondition(userMT4Account);
	}

	@Override
	public List<Integer> findLoginsByNewNameAccountCondition(HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findLoginsByNewNameAccountCondition(userMT4Account);
	}
    @Override
    public int getCountByEmailAndDataSourceType(int userId) {
        return userMT4AccountDao.getCountByEmailAndDataSourceType(userId);
    }

    @Override
	public UserMT4Account getByMt4Account(int account, Integer dataSourceId) {
		UserMT4Account userMt4Account = userMT4AccountDao.getByMt4Account2(account,dataSourceId);
		return userMt4Account;
	}

	@Override
	public List<UserMT4Account> getuserMt4AccountByUserStatusTrader(HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findList("getuserMt4AccountByUserStatusTrader",userMT4Account);
	}
	@Override
	public List<UserMT4Account> getuserMt4AccountByUserStatusTraderSymbol(HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findList("getuserMt4AccountByUserStatusTraderSymbol",userMT4Account);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUidList(List<UserRegister> uidList) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("uidList", uidList);
		List<UserMT4Account> userMt4Account = userMT4AccountDao.getUserMT4AccountByUidList(params);
		Integer flag =null;
		//TODO 添加获取账户余额信息
		if(flag !=null){
			if(userMt4Account !=null && !userMt4Account.isEmpty()){
				List<Integer>logins = new ArrayList<Integer>();
				for (UserMT4Account userMT4Account2 : userMt4Account) {
					logins.add(userMT4Account2.getMt4Account());
				}
				List<Mt4Users>  mt4Users =   mt4UsersDao.getMt4UsersByLogin(logins);
				for (Mt4Users mt4Users2 : mt4Users) {
					//TODO 获取id组装对象
					for (int i = 0; i < userMt4Account.size(); i++) {
						if(mt4Users2.getLogin()== userMt4Account.get(i).getMt4Account()){
							if(mt4Users2.getBalance() == null){
								userMt4Account.get(i).setAccountBalance( new BigDecimal(0.0D));
								continue;
							}else{
								userMt4Account.get(i).setAccountBalance( new BigDecimal(mt4Users2.getBalance()));
								continue;
							}
						}
					}
				}

			}
		}
		return userMt4Account;
	}

	@Override
	public List<UserMT4Account> findUidListByCondition(TaskManage taskManage) {
		return userMT4AccountDao.findList("findUidListByCondition",taskManage);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUIdAndUserStatusAll(int uid, int userStatus) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", uid);
		params.put("userStatus", userStatus);
		return userMT4AccountDao.findList("getUserMT4AccountByUIdAndUserStatusAll",params);
	}

	@Override
    public int getMT4AccountPassCountByUid(int uid) {
        return userMT4AccountDao.getMT4AccountPassCountByUid(uid);
    }

    @Override
    public int getMT4AccountCountByUid(int uid) {
        return userMT4AccountDao.getMT4AccountCountByUid(uid);
    }

    @Override
    public List<UserMT4Account> getUserMT4AccountByUidOrderByAudit(Integer userId) {
        return userMT4AccountDao.getUserMT4AccountByUidOrderByAudit(userId);
    }

	@Override
	public List<UserMT4Account> getUserMt4AccountByNOAgent() {
		return userMT4AccountDao.findList("getUserMt4AccountByNOAgent",null);
	}

    @Override
    public PageIterator<UserMT4Account> pageQueryByConditionByAgentAccount(int agentMt4Account, Pagination pagination) {
        Map<String, Object> params = new HashMap<>();
        params.put("agentAccount", agentMt4Account);
//        int totalCount = userMT4AccountDao.queryCountByConditiontByIbidAndAgentAccount(params);
        int totalCount = userMT4AccountDao.queryCountByConditionByAgentAccount(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryByConditionByAgentAccount(params);
        PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(userMT4AccountList);
        return page;
    }

    @Override
    public List<UserMT4Account> getUserMT4AccountByUIdAndUserStatus(Integer uid, int userStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("userStatus", userStatus);
        return userMT4AccountDao.findList("getUserMT4AccountByUIdAndUserStatus", params);
    }

	@Override
	public List<UserMT4Account> getAllCommissionMT4() {
		return userMT4AccountDao.findList("getAllCommissionMT4",null);
	}

	@Override
	public List<UserMT4Account> getuserMt4AccountByUserStatus(
			HashMap<String, Object> params) {
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params",params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findList("getuserMt4AccountByUserStatus",userMT4Account);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByAgentAccount(int mt4Account, Integer dataSourceId) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("agentAccount", mt4Account);
		params.put("dataSourceId", dataSourceId);
		UserMT4Account userMT4Account = new UserMT4Account();
		userMT4Account.getSqlMap().put("params", params);
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u"));
		return userMT4AccountDao.findList("getUserMT4AccountByAgentAccount",userMT4Account);
	}

	@Override
	public List<UserMT4Account> selectAllAndSuccDeposit(String mt4Account) {
		return userMT4AccountDao.selectAllAndSuccDeposit(mt4Account);
	}

	@Override
	public UserMT4Account getMT4AccountByUidAccount(Integer userId,
			int mt4account, int mt4DatasourceType) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("mt4account", mt4account);
		params.put("mt4DatasourceType", mt4DatasourceType);
		return (UserMT4Account)userMT4AccountDao.findOne("getMT4AccountByUidAccount", params);
	}

	@Override
	public UserMT4Account getByMt4AccountComp(int login) {
		UserMT4Account userMt4Account = userMT4AccountDao.getByMt4AccountComp(login);
		return userMt4Account;
	}

	@Override
	public UserMT4Account getUserMT4AccountByMt4Accounts(Integer login,
			Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("mt4Account", login);
		params.put("dataSourceId", id);
		 return (UserMT4Account) userMT4AccountDao.findOne("getUserMT4AccountByMt4Accountss", params);
	}


	@Override
	public List<UserMT4Account> getUserMT4AccountByBingStatus(
			UserMT4Account userMT4Account) {
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return userMT4AccountDao.findList("getUserMT4AccountByBingStatus", userMT4Account);
	}

    @Override
    public List<UserMT4Account> getUserMT4AccountByUserIdAndIbId(Integer userId, int ib_id) {
        return userMT4AccountDao.getUserMT4AccountByUserIdAndIbId(userId, ib_id);
    }

	@Override
	public int queryCountByConditionBingStatus(UserMT4Account userMT4Account) {
		userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return (int) userMT4AccountDao.findOne("queryCountByConditionBingStatus", userMT4Account);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUid(int uid)
			throws PayException {
		return getUserMT4AccountByUid(uid,null);
	}

    @Override
    public List<UserMT4Account> getUserMT4AccountByIbIdLoop(Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        return userMT4AccountDao.findList("getUserMT4AccountByIbIdLoop", params);
    }

    @Override
    public List<UserMT4Account> getUserMT4AccountByMt4AccountLoop(Map<String, Object> params) {
        return userMT4AccountDao.findList("getUserMT4AccountByMt4AccountLoop", params);
    }

    @Override
    public PageIterator<UserMT4Account> pageQueryByConditiontByUserId(Integer userId, Pagination pagination) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        int totalCount = userMT4AccountDao.queryCountByConditiontByUserId(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryByConditiontByUserId(params);
        PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(userMT4AccountList);
        return page;
    }

    @Override
    public PageIterator<UserMT4Account> pageQueryByConditionByMT4Account(int mt4account, Pagination pagination) {
        Map<String, Object> params = new HashMap<>();
        params.put("mt4account", mt4account);
        int totalCount = userMT4AccountDao.queryCountByConditionByMt4Account(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserMT4Account> userMT4AccountList = userMT4AccountDao.pageQueryByConditionByMt4Account(params);
        PageIterator<UserMT4Account> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(userMT4AccountList);
        return page;
    }

	@Override
	public List<UserMT4Account> getUserMT4AccountByLoginList(
			Map<String,Object> params) {
		return userMT4AccountDao.findList("getUserMT4AccountByLoginList", params);
	}
}