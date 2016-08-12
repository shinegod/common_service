package com.fx.mt4TradeRecord.service.impl;

import com.fx.MT4.vo.MT4User;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.mt4TradeRecord.dao.IMt4UsersDao;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.payment.dao.IUserMT4AccountDao;
import com.fx.payment.model.UserMT4Account;
import com.fx.user.dao.IUserRegisterDao;
import com.fx.user.model.UserRegister;
import com.fx.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Mt4UsersServiceImpl extends BaseVOService<Mt4Users> implements IMt4UsersService {
    @Autowired
    private IMt4UsersDao mt4UsersDao;

    @Autowired
    private IUserMT4AccountDao userMT4AccountDao;

    @Autowired
    private IDataSourceBeanService dataSourceBeanService;
    @Autowired
    private IUserRegisterDao userRegisterDao;

	@Override
	public Mt4Users getMt4UsersByMt4Account(int mt4Account, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4UsersDao.getMt4UsersByMt4Account(mt4Account);
	}

	@Override
	public Pagination<Mt4Users> findByPagination(Pagination pagination, Map<String, Object> params, String dataRule, int dataSource) {
        DataRuleLevel dataRuleLevel = new DataRuleLevel();
        dataRuleLevel.setLevelByString(dataRule);
//        if (DataRuleLevel.Level.UNDER_LEVEL.equals(dataRuleLevel.getLevel())) {
//            params.put(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY, dataRule);
        return getUnderLevelData(pagination, params, dataSource);
//        }
//        return getAllData(pagination, params, dataSource);
    }

    private Pagination<Mt4Users> getUnderLevelData(Pagination pagination, Map<String, Object> params, int dataSource) {
        // 根据不同数据源连接不同数据库
        UserMT4Account userMT4Account = new UserMT4Account();
        Mt4Users findmt4Users = new Mt4Users();
        Map findUsersMap = new HashMap();
        multiDataSourceSet(dataSource);
        userMT4Account.getSqlMap().put("params", params);

        if(pagination.getIbId_all() != -1 && pagination.getIbId()==-2) {
            userMT4Account.getSqlMap().put("ibId", pagination.getIbId_all());
        } else if(pagination.getIbId()== -1) {
        } else{
            int ibId = pagination.getIbId();
            userMT4Account.getSqlMap().put("ibId", ibId);
            userMT4Account.getSqlMap().put("flagIbid", 1);
        }
        if(!pagination.getQueryConfig().equals("0")){
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            Map<String,String> map = new LinkedHashMap<String,String>();
            map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
            if(map.containsKey("agentaccount_QUERY_EQUALS")){
                //params.put("ibId", map.get("agentaccount_QUERY_EQUALS"));
                userMT4Account.getSqlMap().put("agentId", StringUtils.trim(map.get("agentaccount_QUERY_EQUALS")));
            }
        }

        if(!pagination.getQueryConfig().equals("0")){
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            Map<String,String> map = new LinkedHashMap<String,String>();
            map = gson.fromJson(pagination.getQueryConfig(),new TypeToken<Map<String,String>>(){}.getType());
            if(map.containsKey("mt4group_QUERY_EQUALS")){
                //params.put("mt4groupId", map.get("custcateId_QUERY_EQUALS"));
                userMT4Account.getSqlMap().put("mt4groupId",StringUtils.trim(map.get("mt4group_QUERY_EQUALS")));
            }
            if(map.containsKey("mt4group_QUERY_NOTEQUALS")){
                //params.put("nomt4groupId", map.get("mt4group_QUERY_NOTEQUALS"));
                userMT4Account.getSqlMap().put("nomt4groupId",StringUtils.trim(map.get("mt4group_QUERY_NOTEQUALS")));
            }
            if(map.containsKey("accounttime_QUERY_EQUALS")){
                //params.put("accounttime_QUERY_EQUALS", map.get("accounttime_QUERY_EQUALS"));
                userMT4Account.getSqlMap().put("accounttime_QUERY_EQUALS",StringUtils.trim(map.get("accounttime_QUERY_EQUALS")));
                String startTime = map.get("accounttime_QUERY_EQUALS")+" 00:00:00";
                String endTime = map.get("accounttime_QUERY_EQUALS")+" 23:59:59";
                //params.put("startTimeEquals", startTime);
                //params.put("endTimeEquals", endTime);
                userMT4Account.getSqlMap().put("startTimeEquals",startTime);
                userMT4Account.getSqlMap().put("endTimeEquals",endTime);
            }
            if(map.containsKey("accounttime_QUERY_MORE")){
                //params.put("accounttime_QUERY_MORE", map.get("accounttime_QUERY_MORE"));
                userMT4Account.getSqlMap().put("accounttime_QUERY_MORE",StringUtils.trim(map.get("accounttime_QUERY_MORE")));
                String startTime = map.get("accounttime_QUERY_MORE")+" 23:59:59";
                //params.put("startTimeMore", startTime);
                userMT4Account.getSqlMap().put("startTimeMore",startTime);
            }
            if(map.containsKey("accounttime_QUERY_LESS")){
                //params.put("accounttime_QUERY_LESS", map.get("accounttime_QUERY_LESS"));
                userMT4Account.getSqlMap().put("accounttime_QUERY_LESS",StringUtils.trim(map.get("accounttime_QUERY_LESS")));
                String startTime = map.get("accounttime_QUERY_LESS")+" 00:00:00";
                //params.put("startTimeLess", startTime);
                userMT4Account.getSqlMap().put("startTimeLess",startTime);
            }
            if(map.containsKey("leverage_QUERY_EQUALS")){
                findUsersMap.put("leverage_QUERY_EQUALS",map.containsKey("leverage_QUERY_EQUALS"));
                if(StringUtils.trim(map.get("leverage_QUERY_EQUALS")).indexOf("_")!=-1){
                    findmt4Users.setLeverage(Integer.parseInt(map.get("leverage_QUERY_EQUALS").substring(StringUtils.trim(map.get("leverage_QUERY_EQUALS")).indexOf("_")+1)));
                }
            }
            if(map.containsKey("leverage_QUERY_NOTEQUALS")){
                findUsersMap.put("leverage_QUERY_NOTEQUALS",map.containsKey("leverage_QUERY_NOTEQUALS"));
                if(StringUtils.trim(map.get("leverage_QUERY_NOTEQUALS")).indexOf("_")!=-1){
                    findmt4Users.setLeverage(Integer.parseInt(map.get("leverage_QUERY_NOTEQUALS").substring(StringUtils.trim(map.get("leverage_QUERY_NOTEQUALS")).indexOf("_")+1)));
                }
            }
            if(map.containsKey("balance_QUERY_EQUALS")){
                findUsersMap.put("balance_QUERY_EQUALS",map.containsKey("balance_QUERY_EQUALS"));
                findmt4Users.setBalance(Double.parseDouble(StringUtils.trim(map.get("balance_QUERY_EQUALS"))));
            }
            if(map.containsKey("balance_QUERY_MORE")){
                findUsersMap.put("balance_QUERY_MORE",map.containsKey("balance_QUERY_MORE"));
                findmt4Users.setBalance(Double.parseDouble(StringUtils.trim(map.get("balance_QUERY_MORE"))));
            }
            if(map.containsKey("balance_QUERY_LESS")){
                findUsersMap.put("balance_QUERY_LESS",map.containsKey("balance_QUERY_LESS"));
                findmt4Users.setBalance(Double.parseDouble(StringUtils.trim(map.get("balance_QUERY_LESS"))));
            }
            if(map.containsKey("balance_QUERY_NOMORE")){
                findUsersMap.put("balance_QUERY_NOMORE",map.containsKey("balance_QUERY_NOMORE"));
                findmt4Users.setBalance(Double.parseDouble(StringUtils.trim(map.get("balance_QUERY_NOMORE"))));
            }
            if(map.containsKey("balance_QUERY_NOLESS")){
                findUsersMap.put("balance_QUERY_NOLESS",map.containsKey("balance_QUERY_NOLESS"));
                findmt4Users.setBalance(Double.parseDouble(StringUtils.trim(map.get("balance_QUERY_NOLESS"))));
            }
        }
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        //params.put("offset", offset);
        userMT4Account.getSqlMap().put("offset",offset);
        //params.put("limit", pageSize);
        userMT4Account.getSqlMap().put("limit",pageSize);
        userMT4Account.getSqlMap().put("dataSource", dataSource);
        userMT4Account.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org", "u1"));
//        PageHelper.startPage((pagination.getOffset() / pagination.getLimit() + 1), pagination.getLimit());
        //List<UserMT4Account> userMT4AccountList = userMT4AccountDao.queryByConditionMoreTable(userMT4Account);
 /*       for (UserMT4Account userMT4Account2 : userMT4AccountList) {
            if (null != userMT4Account2) {
                findmt4Users.setLogin(userMT4Account2.getMt4Account());
                findUsersMap.put("findmt4Users",findmt4Users);
                Mt4Users mt4Users = mt4UsersDao.getMt4UsersByMt4AccountAndBalanceLeverage(findUsersMap);
                if (null != mt4Users) {
                    mt4Users.setUid(userMT4Account2.getUid());
                    mt4UsersList.add(mt4Users);
                }
            }
        }*/
        findUsersMap.put("findmt4Users",findmt4Users);
        List<Mt4Users> mt4UsersList = new ArrayList<>();
        List<Integer> loginlist = new ArrayList<>();
        Map userIdMap = new HashMap();


        List<UserMT4Account> userMT4AccountList = userMT4AccountDao.queryLoginList(userMT4Account);

        for (UserMT4Account userMT4Account2 : userMT4AccountList){
            loginlist.add(userMT4Account2.getMt4Account());
            userIdMap.put(userMT4Account2.getMt4Account(),userMT4Account2.getUid());
        }
        findUsersMap.put("loginlist",loginlist);
        if(params.get("orderByName") != null) {
            findUsersMap.put("orderByName",params.get("orderByName"));
        }
        PageHelper.startPage((pagination.getOffset() / pagination.getLimit() + 1), pagination.getLimit());
        mt4UsersList =  mt4UsersDao.getMt4UsersByMt4AccountAndBalanceLeverage(findUsersMap);
 

        for(Mt4Users mt4Users :mt4UsersList){
            if(userIdMap.containsKey(mt4Users.getLogin())){
                mt4Users.setUid((Integer)userIdMap.get(mt4Users.getLogin()));
            }
        }
//        PageInfo pageIterator = new PageInfo(userMT4AccountList);
//        pagination.setTotal(totalCount);
//        pagination.setRows(mt4UsersList);
        PageInfo pageIterator = new PageInfo(mt4UsersList);
        pagination.setTotal((int) pageIterator.getTotal());
        pagination.setRows(mt4UsersList);
  /*      int totalCount = mt4UsersList.size();
        PageIterator<Mt4Users> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(mt4UsersList);*/
        return pagination;
    }

    @Override
	public int queryPageSize(Map<String, Object> params, Integer dataSource){
    	multiDataSourceSet(dataSource);
		return mt4UsersDao.queryPageSize(params);
	}

    @Override
    public Integer repeatMT4Account(int login) {
        return mt4UsersDao.repeatMT4Account(login);
    }

    public PageIterator<Mt4Users> getAllData(Pagination pagination,Map<String, Object> params, int dataSource) {
        multiDataSourceSet(dataSource);
        int totalCount = mt4UsersDao.queryPageSize(params);
        int offset = pagination.getOffset();
        int pageSize = pagination.getLimit();
        int pageNo = (offset / pageSize) + 1;
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<Mt4Users> mt4UsersList = mt4UsersDao.findByPagination(params);
        PageIterator<Mt4Users> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
        page.setData(mt4UsersList);
        return page;
    }

	@Override
	public List<Mt4Users> getMt4UsersByLogin(List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return mt4UsersDao.getMt4UsersByLogin(login);
	}


	@Override
	public List<Mt4Users> queryNewAccounts(HashMap<String, Object> paramTrade,
			int dataSource) {
		 multiDataSourceSet(dataSource);
		 return mt4UsersDao.findList("queryNewAccounts", paramTrade);
	}

    @Override
    public List<Mt4Users> queryAllAccounts(HashMap<String, Object> paramTrade,
                                           int dataSource) {
        multiDataSourceSet(dataSource);
        return mt4UsersDao.findList("queryAllAccounts", paramTrade);
    }
    @Override
    public List<Integer> findLoginByDate(HashMap<String, Object> paramTrade,
                                           int dataSource) {
        multiDataSourceSet(dataSource);
        return mt4UsersDao.findList("findLoginByDate", paramTrade);
    }

    @Override
	public Double getTotalBalance(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4UsersDao.findOne("getTotalBalance", paramTrade);
	}

	@Override
	public Double getTotalFloatingProfitLoss(
			HashMap<String, Object> paramTrade, int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4UsersDao.findOne("getTotalFloatingProfitLoss", paramTrade);
	}

	@Override
	public Double getTotalEquity(HashMap<String, Object> paramTrade,
			int dataSource) {
		multiDataSourceSet(dataSource);
		return (Double) mt4UsersDao.findOne("getTotalEquity", paramTrade);
	}

    @Override
    public Mt4Users findByEmail(Mt4Users mt4User,int dataSource) {
        multiDataSourceSet(dataSource);
        return (Mt4Users) mt4UsersDao.findOne("findByEmail", mt4User);
    }
}