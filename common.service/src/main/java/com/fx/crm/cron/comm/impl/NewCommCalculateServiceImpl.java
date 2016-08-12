package com.fx.crm.cron.comm.impl;

import com.fx.MT4.util.MT4GroupUtil;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.bonus.enums.IBCommissionStatusEnum;
import com.fx.bonus.enums.IsInnerSalesEnum;
import com.fx.bonus.model.*;
import com.fx.bonus.service.*;
import com.fx.crm.comm.model.CommissionAccountDetail;
import com.fx.crm.comm.model.CommissionBase;
import com.fx.crm.comm.model.CommissionMonth;
import com.fx.crm.comm.model.UserCommissionConf;
import com.fx.crm.comm.service.ICommissionAccountDetailService;
import com.fx.crm.comm.service.ICommissionBaseService;
import com.fx.crm.comm.service.ICommissionMonthService;
import com.fx.crm.comm.service.IUserCommissionConfService;
import com.fx.crm.cron.comm.ICommCalculateService;
import com.fx.dataSourceBean.enums.DataSourceTypeEnum;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.mt4TradeRecord.model.Mt4Prices;
import com.fx.mt4TradeRecord.model.Mt4Trades;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.mt4TradeRecord.service.IMt4PricesService;
import com.fx.mt4TradeRecord.service.IMt4TradesService;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.multidatasource.DataSourceContextHolder;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.payment.util.UserMT4StatusEnum;
import com.fx.user.enums.RulesCommissionTypeEnum;
import com.fx.user.enums.UserAccountTypeEnum;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.user.service.IUserService;
import com.fx.user.service.impl.UserRegisterServiceImpl;
import com.fx.util.CacheMgr;
import com.fx.util.RateUtil;
import com.fx.util.SpringUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/6/1.
 */
@Service
public class NewCommCalculateServiceImpl implements ICommCalculateService {

    private static final Logger logger = LoggerFactory.getLogger(NewCommCalculateServiceImpl.class);

    private static final String DEFAULT_DAY_END = " 00:00:00";
    
    private static final String DEFAULT_DAY_BEGIN = " 00:00:01";

    @Autowired
    private IMt4TradesService mt4TradesService;

    @Autowired
    private ICommissionBaseService commissionBaseService;

    @Autowired
    private ICommissionAccountDetailService commissionAccountDetailService;

    @Autowired
    private IUserCommissionConfService userCommissionConfService;
    
    @Autowired
    private ITradingGroupService tradingGroupService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ICommissionSpecialRulesService commissionSpecialRulesService;
    
    @Autowired
    private IPipSettingService pipSettingService;

	@Autowired
	private IMt4UsersService mt4UsersService;

    @Autowired
    private IMt4PricesService mt4PricesService;
    
    @Autowired
    private ITradeDictionaryService tradeDictionaryService;
    
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IAdminService adminService;
    
    @Autowired
    private ITradeCurrencyDictionaryService iTradeCurrencyDictionaryService;
    
    @Autowired
    private ICommissionMonthService commissionMonthService;
    
    @Autowired
    private ICommissionSpecialService commissionSpecialService;
    
    @Autowired
    private ICommisionCoefficientService commisionCoefficientService;
    
    @Autowired
    private ICommissionRulesService commisionGroupService;
    
    @Autowired
    private IUserMT4AccountService userMT4AccountService;

    @Autowired
    private IUserRegisterService userRegisterService;
    
    @Autowired
    private IDataSourceBeanService dataSourceService;
    
    @Autowired
    private IProfitCommissionService profitCommissionService;

    @Override
    public void calCommission(Date begin, Date end) throws ParseException {
        //1. 设置时间
        Date date1 = setDateTime(begin,DEFAULT_DAY_BEGIN); 
        Date date2 = setDateTime(end,DEFAULT_DAY_END);
        
        //2. 多数据源集合
        List<DataSourceBean> dataSourceBeanList = new ArrayList<DataSourceBean>();
        dataSourceBeanList = getAllDataSource(dataSourceBeanList);
        logger.info("多数据源的个数count:{}", dataSourceBeanList.size());
//        Map<Integer,Set<String>> map = getGrou(dataSourceBeanList);
//        logger.info("mt4账号组获取，mt4组的大小,count:{}",map.size());
        //2.1 遍历数据源集合
        if(null!=dataSourceBeanList&&dataSourceBeanList.size()>0){
        	for(DataSourceBean dataSourceBean:dataSourceBeanList){
                //2.3 获取mt4组
        		Set<String> groupSet = null;
//        		groupSet = getGroupset(map,dataSourceBean,groupSet);
        		
        		//3. 每日计算佣金
        		commissionDayHandNumber(date1,date2,dataSourceBean,groupSet, dataSourceBeanList);
        	}
        }
    }
    
    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
    	List<Integer> uidList = new ArrayList<Integer>();
    	IUserRegisterService userRegisterService = SpringUtils.getBean(UserRegisterServiceImpl.class);
    	NewCommCalculateServiceImpl commCalculateService = SpringUtils.getBean(NewCommCalculateServiceImpl.class);
    	UserRegister userRegister = userRegisterService.getLiveByEmail("ib2@2.com");
    	uidList = commCalculateService.getDownUserId(uidList,userRegister,7820715);
    	String str="";
    	for(Integer uid:uidList){
    		str+=uid+",";
    	}
    	System.out.println(str);
	}
    
    private List<DataSourceBean> getAllDataSource(List<DataSourceBean> dataSourceBeanList){
    	HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("sourceWebSideType", DataSourceTypeEnum.PERSONAL_ACCOUNT.getValue());
        params.put("databaseType", "MySQL");
        dataSourceBeanList = dataSourceService.getDataSourceBeanByParams(params);
    	return dataSourceBeanList;
    }
    
    /**
     * 设置时间
     * @param begin
     * @param start
     * @throws ParseException
     */
    private Date setDateTime(Date begin,String start) throws ParseException{
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String str1 = sf.format(begin);
        Date date1 = sf2.parse(str1+start);
        return date1;
    }
    
    /**
     * 获取mt4组集合
     * @param map
     * @param dataSourceBean
     */
    private Set<String> getGroupset(Map<Integer,Set<String>> map,DataSourceBean dataSourceBean,Set<String> groupSet){
		try{
			groupSet = map.get(dataSourceBean.getId());
			logger.info("该数据源下的mt4账号组，数据源名:{},mt4组的个数:{}",dataSourceBean.getDataName(),groupSet.size());
		}catch(Exception e){
			logger.error("====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",new Date(),e.getLocalizedMessage() + "----" + e.getMessage());
		}
		return groupSet;
    }
    
    /**
     * 取到一天的交易记录，如果上级存在进行返佣
     * @param date1
     * @param date2
     * @param dataSourceBean
     * @param groupSet
     * @param dataSourceBeanList
     */
    private void commissionDayHandNumber(Date date1,Date date2,DataSourceBean dataSourceBean,Set<String> groupSet,List<DataSourceBean> dataSourceBeanList){


		//todo 3.1 获取当天交易记录
		long startTimeLong = System.currentTimeMillis();
    	List<Mt4Trades> tradesList = mt4TradesService.getMt4TradesByCloseTime(date1, date2,dataSourceBean.getId());
		logger.info("Time---getMt4TradesByCloseTime:{}", System.currentTimeMillis() - startTimeLong);
        logger.info("从start:{}到end:{}这段时间产生的交易记录的个数count:{}",date1,date2,tradesList.size());

        //todo 3.2 遍历交易记录集合
        for(Mt4Trades trades : tradesList){
    		logger.info("交易记录的mt4账号login:{},循环开始 :Time:{}",trades.getLogin(),System.currentTimeMillis()-startTimeLong);
        	//todo 3.3 获取对应的mt4账号记录
        	UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Accounts(trades.getLogin(),dataSourceBean.getId());
        	logger.info("对应的数据库中的账号记录是:{}",userMT4Account);

        	if(null!=userMT4Account){
        		//3.4 如果存在，获取对应的注册记录和用户记录
        		UserRegister userRegister = userRegisterService.findById(userMT4Account.getUid());
        		User user = userService.getByUid(userRegister.getId());
        		logger.info("该MT4账号对应的用户注册信息:{}，用户信息:{}",userRegister,user);
        		//todo 是否是真实用户
        		if(null!=userRegister && null!=user){

        			//todo 3.5 获取该用户该MT4账号的直属上级
        			UserRegister userRegister2 = null;
        			userRegister2 = getUserUpterUserRegister(userRegister,userRegister2,userMT4Account);

        			//todo 3.6 获取交易记录的货币对和品种组
            		int tradingGroupId = -1;
    				String symbol = "";
    				tradingGroupId = (int)getSymbolsTradingGroup(tradingGroupId,symbol,trades,userMT4Account,1);
    				symbol = (String)getSymbolsTradingGroup(tradingGroupId,symbol,trades,userMT4Account,2);

    				//todo 如果获取的上级IB或者该用户不存在，跳过
    				if(null!=userRegister2){

    					//todo 4. 佣金计算的处理方法
						startTimeLong = System.currentTimeMillis();
    					insertcommissionMethod(userRegister2,userMT4Account,user,tradingGroupId,symbol,trades,dataSourceBeanList,groupSet,date1);
						logger.info("交易记录的mt4账号login:{}, 佣金计算的处理方法:insertcommissionMethod  --Time:{}",trades.getLogin(),System.currentTimeMillis()-startTimeLong);
    				}
        		}
        	}
			logger.info("交易记录的mt4账号login:{},循环结束 :Time:{}",trades.getLogin(),System.currentTimeMillis()-startTimeLong);
        }
    }
    
    /**
     * 判断当前交易记录采用哪种返佣
     */
    private void insertcommissionMethod(UserRegister userRegister2,UserMT4Account userMT4Account,User user,int tradingGroupId,String symbol,Mt4Trades trades,List<DataSourceBean> dataSourceBeanList,Set<String> groupSet,Date date1){
    	long timeLong  = System.currentTimeMillis();
		logger.info("insertcommissionMethod--start time :{}",timeLong);
		List<UserCommissionConf> userCommissionConfList = new ArrayList<UserCommissionConf>();

		//todo 4.1 获取该IB的所用佣金计算的规则(中间表)
    	userCommissionConfList = getUserCommissionConf(userMT4Account,userRegister2,userCommissionConfList);
		logger.info("该用户的直属IB对应的规则，uid:{},返佣账号:{},对应规则的个数:{}",userRegister2.getId(),userMT4Account.getAgentMt4Account(),userCommissionConfList.size());

		//todo 判断使用那个具体的规则计算
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sToday = sf.format(date1);

		//todo 4.2 遍历集合
		for(UserCommissionConf userCommissionConf : userCommissionConfList){

			//todo 获取指定规则
			CommissionRules commissionGroup = commisionGroupService.findById(userCommissionConf.getCommRuleId());
			logger.info("返佣的规则:{}",commissionGroup);


			if(userCommissionConf.getCommType().equals("1")){
				if(userRegister2.getWebsiteUserType()==5){
					if(null==commissionGroup  ||  commissionGroup.getDataSourceId()!=userMT4Account.getDataSourceId()){
						continue;
					}
				}

				//todo 根据 产品组id 筛选出指定 的返佣规则链 (手数返佣) userdefine1 = 1
				if(null!=commissionGroup && (commissionGroup.getUserdefine1().equals("1")) && commissionGroup.getStatus()==0  && tradingGroupId==commissionGroup.getTradingGroupId()){
					//按点值计算
					if(commissionGroup.getSettleMode().equals("POINT")){
						logger.info("commissionmethod POINT--start time :{}",System.currentTimeMillis()-timeLong);

						//todo 点值计算 settleModel = POINT
						commissionmethod(commissionGroup.getSettleMode(),user,trades,userMT4Account,symbol,userCommissionConf,groupSet,tradingGroupId ,commissionGroup,userRegister2, dataSourceBeanList);
						logger.info("commissionmethod POINT--end time :{}",System.currentTimeMillis()-timeLong);
					}else if(commissionGroup.getSettleMode().equals("FIXED")){ //手数返佣
						logger.info("commissionmethod FIXED--start time :{}",System.currentTimeMillis()-timeLong);

						//todo 手数(固定值计算) settleModel = FIXED
						commissionmethod("FIXED",user,trades,userMT4Account,symbol,userCommissionConf,groupSet,tradingGroupId ,commissionGroup,userRegister2, dataSourceBeanList);
						logger.info("commissionmethod FIXED--end time :{}",System.currentTimeMillis()-timeLong);
					}else if(commissionGroup.getSettleMode().equals("PERCENTAGE")){
						//todo 百分比返佣(待用)
					}
				}
			}
			else if(userCommissionConf.getCommType().equals("2")){
				if(userRegister2.getWebsiteUserType()==5){
					if(null==commissionGroup  ||  commissionGroup.getDataSourceId()!=userMT4Account.getDataSourceId()){
						continue;
					}
				}

				//todo 附加返佣  userdefine1 = 2
				if(null!=commissionGroup && commissionGroup.getUserdefine1().equals("2") && commissionGroup.getStatus()==0 && tradingGroupId==commissionGroup.getTradingGroupId()){
					//todo 附加返佣 计算方式   PERCENTAGE
					if(commissionGroup.getSettleMode().equals("PERCENTAGE")){
						logger.info("profitCommissionMethod PERCENTAGE--start time :{}",System.currentTimeMillis()-timeLong);
						profitCommissionMethod(userCommissionConf,userMT4Account,trades,sToday);
						logger.info("profitCommissionMethod PERCENTAGE--end time :{}",System.currentTimeMillis()-timeLong);
					}
				}
			}
		}
		logger.info("insertcommissionMethod--end time :{}",System.currentTimeMillis()-timeLong);
    }

    private List<UserCommissionConf> getUserCommissionConf(UserMT4Account userMT4Account,UserRegister userRegister2,List<UserCommissionConf> userCommissionConfList){
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("userId", userRegister2.getId());
    	if((int)userRegister2.getId()==userMT4Account.getUid()){
    		List<UserMT4Account> userMT4AccountList = userMT4AccountService.selectMt4AccountByStatus(userRegister2.getId(), UserMT4StatusEnum.IB_COMMISSION.getValue(), userRegister2.getMt4DataSourceId());
    		if(null!=userMT4AccountList && userMT4AccountList.size()>0){
    			param.put("agentAccount", userMT4AccountList.get(0).getMt4Account()+"");
    		}else{
    			return userCommissionConfList;
    		}
    	}else{
    		param.put("agentAccount", userMT4Account.getAgentMt4Account()+"");
    	}
		
		userCommissionConfList = userCommissionConfService.findByUidAccount(param);
    	return userCommissionConfList;
    }
    
    /**
     * 获取产生交易记录的直属上级
     */
    private UserRegister getUserUpterUserRegister(UserRegister userRegister,UserRegister userRegister2,UserMT4Account userMT4Account){
    	if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			if(userMT4Account.getIbId()==userRegister.getId()){
				userRegister2 = userRegister;
				logger.info("上级IB的编号uid:{}",userRegister2);
			}else{
				userRegister2 = userRegisterService.findById(userMT4Account.getIbId());
				logger.info("上级IB的编号uid:{}",userRegister2);
			}
		}else if(userRegister.getIs_ibId()>0){
			userRegister2 = userRegisterService.findById(userMT4Account.getIbId());
			logger.info("上级IB:{}",userRegister2);
		}
    	return userRegister2;
    }
    
    /**
     * 返回交易记录的货币对或者品种组
     */
    private Object getSymbolsTradingGroup(int tradingGroupId,String symbol,Mt4Trades trades,UserMT4Account userMT4Account,int type){
    	List<TradingGroup> tradingGroupList = tradingGroupService.getTradeGroupByDataSource(userMT4Account.getDataSourceId());
    	for(TradingGroup tradingGroup:tradingGroupList){
			if(tradingGroup.getTradingCategory().indexOf(trades.getSymbol()) != -1){
				tradingGroupId = tradingGroup.getId();
				symbol = trades.getSymbol();
				logger.info("交易记录交易的货币对属于哪个品种组，交易货币对:{},交易品种组Id:{}",symbol,tradingGroupId);
				break;
			}
		}
    	if(type==1){
    		return tradingGroupId;
    	}else{
    		return symbol;
    	}
    }
    
    /**
     * 根据IB用户进行汇总
     */
    @Override
	public void calculateMonthlyIbCommission(Calendar today) throws ParseException, PayException {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar yesterday=(Calendar) today.clone();
		yesterday.add(Calendar.DAY_OF_MONTH,-1);
		Calendar ctoday=(Calendar) today.clone();
		String sToday = sf.format(ctoday.getTime());
		String sYesterday=sf.format(yesterday.getTime());

		//todo 获取去所有的 ib 用户
    	List<UserRegister> userRegisterList = userRegisterService.getByWebsiteUserTypess(UserAccountTypeEnum.IB_ACCOUNT.getValue());
    	logger.info("网站IB用户的个数count:{}", userRegisterList.size());

		//todo 遍历
		for(UserRegister userRegister:userRegisterList){
			logger.info("当前IB用户的编号uid:{},现在的时间time:{}",userRegister.getId(),sToday);
			monthCommission(userRegister,sYesterday,sf,ctoday,sToday,yesterday);
		}

		//todo 获取所有的 sale 信息
		List<UserRegister> salesUserRegister = userRegisterService.getByWebsiteUserTypess(UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue());
		logger.info("网站sales用户的个数count:{}",salesUserRegister.size());
		if(null!=salesUserRegister&& salesUserRegister.size()>0){
			for(UserRegister salesUserRegi:salesUserRegister){
				logger.info("当前IB用户的编号uid:{},现在的时间time:{}",salesUserRegi.getId(),sToday);
				monthCommission(salesUserRegi,sYesterday,sf,ctoday,sToday,yesterday);
			}
		}
	}
    
    /**
     * 附加返佣的计算记录的插入
     */
    private void insertProfit(UserMT4Account userMt4Account,Mt4Trades trades,String sToday,CommisionCoefficient commisionCoefficient,CommissionSpecialRules commissionSpecialRules,double porgation,String specialId,Mt4Users mt4Users){
    	 ProfitCommission profitCommission = new ProfitCommission();
		 profitCommission.setCreatedate(new Date());
		 profitCommission.setMt4account(userMt4Account.getMt4Account()); 
		 profitCommission.setGuid(UUID.randomUUID().toString());
		 UserRegister userRegister = null;
		Mt4Users mt4Users1 = null;
    	 if(null!=commissionSpecialRules){ 
    		 userRegister = userRegisterService.findById(commissionSpecialRules.getUid());
    		 profitCommission.setAgentaccount(commissionSpecialRules.getAgentAccount());
			 if(commissionSpecialRules.getAgentAccount()>0){
				 mt4Users1 = mt4UsersService.getMt4UsersByMt4Account(commissionSpecialRules.getAgentAccount(), userMt4Account.getDataSourceId());
			 }
    	 }else{
    		 userRegister = userRegisterService.findById(commisionCoefficient.getUid());
    		 profitCommission.setAgentaccount(commisionCoefficient.getAgentAccount());
			 if(commisionCoefficient.getAgentAccount()>0){
				 mt4Users1 = mt4UsersService.getMt4UsersByMt4Account(commisionCoefficient.getAgentAccount(), userMt4Account.getDataSourceId());
			 }

    	 }
		 profitCommission.setIbId(userRegister.getId() + "");
		 if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			profitCommission.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue()+"");
		 }else if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
			profitCommission.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue()+"");
		 }
		String currency ="";
		logger.info("用户mt4账号的记录userMT4Account:{}",userMt4Account);

		currency = getTradeCurrency(mt4Users.getGroup(),currency);
		if(!("".equals(currency))){
			profitCommission.setRate(RateUtil.getCurrencyRate(currency, "USD", userMt4Account.getDataSourceId()));
		}else{
			profitCommission.setRate(new BigDecimal(1.0D));
		}

		String currency2 ="";
		if(null!=mt4Users1){
			currency2 = getTradeCurrency(mt4Users1.getGroup(), currency2);
		}
		if(!("".equals(currency2))){
			profitCommission.setParRate(RateUtil.getCurrencyRate(currency2, "USD", userMt4Account.getDataSourceId()));
		}else{
			profitCommission.setParRate(new BigDecimal(1.0D));
		}
		 profitCommission.setPaymentDate(sToday);
		 profitCommission.setStatus(IBCommissionStatusEnum.NO_PAY.getValue());
		 profitCommission.setUpdatedate(new Date());
		 profitCommission.setUserId(userMt4Account.getUid() + "");
		 profitCommission.setProfitAmount(new BigDecimal(trades.getProfit()));
		 profitCommission.setDataSourceId(userRegister.getMt4DataSourceId());
		 profitCommission.setUnitPrice(new BigDecimal(Double.parseDouble(commisionCoefficient.getCoefficient())));
		 profitCommission.setPorgation(new BigDecimal(porgation));
		 profitCommission.setSpecialId(specialId);
		profitCommission.setSwaps(new BigDecimal(trades.getSwaps()));
		profitCommission.setSymbol(trades.getSymbol());
		profitCommission.setTicket(trades.getTicket() + "");
		profitCommission.setMt4AccountGroup(mt4Users.getGroup());
		profitCommission.setRules(commisionCoefficient.getRuleId());
		profitCommission.setCloseTime(trades.getCloseTime());
		profitCommission.setProfit(new BigDecimal(trades.getProfit()*Double.parseDouble(commisionCoefficient.getCoefficient())*porgation/100.0));
		 profitCommissionService.doInsert(profitCommission);
    }
    
    private Map<Integer, Set<String>> getGrou(List<DataSourceBean> dataSourceBeanList) {
    	
    	Map<Integer,Set<String>> groupMap = CacheMgr.get("group");
    	if(groupMap == null){
    		groupMap = getfromdll(dataSourceBeanList);
    		CacheMgr.put("group", groupMap);
    	}
    	return groupMap;
	}

	private Map<Integer, Set<String>> getfromdll(List<DataSourceBean> dataSourceBeanList) {
		Map<Integer,Set<String>> groupMap = new HashMap<Integer,Set<String>>();
		if(null!=dataSourceBeanList&&dataSourceBeanList.size()>0){
        	for(DataSourceBean dataSourceBean:dataSourceBeanList){
        		Set<String> groupSet = null;
        		try{
        			groupSet = MT4GroupUtil.getGroupWangList(false, dataSourceBean);
        			groupMap.put(dataSourceBean.getId(), new HashSet<String>(groupSet));
        		}catch(Exception e){
        			logger.error("====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",new Date(),e.getLocalizedMessage() + "----" + e.getMessage());
        		}
        		
        	}
        }
		return groupMap;
	}

    @Override
    public void calCommissionAccount(Date begin, Date end) {

    }
    
    /**
     * 插入佣金计算记录的方法
     */
    private void insertIntoDetail(Mt4Trades mt4trader,UserMT4Account userMT4Account,String symbol,CommisionCoefficient commissionCoefficient,int tradingGroupId,String commissionType,Set<String> groupSet,String mode,List<DataSourceBean> dataSourceBeanList,CommissionSpecialRules commissionSpecialRules,double porgation,String specialId,Mt4Users mt4Users){

		logger.info("用户的Mt4Account:{}的所属数据源dataSource:{}",userMT4Account.getMt4Account(),userMT4Account.getDataSourceId());
    	DataSourceBean dataSourceBean = DataSourceContextHolder.getDATASOURCEBEAN_MAP().get(userMT4Account.getDataSourceId());

		//todo 封装对象
		CommissionBase commissionBase = new CommissionBase();
		commissionBase.setCloseTime(mt4trader.getCloseTime());
		UserMT4Account userMT4Account2 = null;
    	UserRegister userRegister = null;
		Mt4Users  mt4Users1 = null;

		//todo 有例外通用方法
		if(null!= commissionSpecialRules){
			userRegister = userRegisterService.findById(commissionSpecialRules.getUid());
			commissionBase.setAgentAccount(commissionSpecialRules.getAgentAccount());
			commissionBase.setComment("例外");
			if(commissionSpecialRules.getAgentAccount()>0){
				userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(commissionSpecialRules.getAgentAccount(), userMT4Account.getDataSourceId());
				mt4Users1 = mt4UsersService.getMt4UsersByMt4Account(commissionSpecialRules.getAgentAccount(), userMT4Account.getDataSourceId());
			}
		}else{
			//todo 没有例外

			//todo 获取返佣的用户信息
			userRegister = userRegisterService.findById(commissionCoefficient.getUid());
			commissionBase.setAgentAccount(commissionCoefficient.getAgentAccount());
			commissionBase.setComment("");

			if(commissionCoefficient.getAgentAccount()!=0){

				//todo 获取该返佣用户的返佣帐号  和 mt4
				userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(commissionCoefficient.getAgentAccount(), userMT4Account.getDataSourceId());
				mt4Users1 = mt4UsersService.getMt4UsersByMt4Account(commissionCoefficient.getAgentAccount(), userMT4Account.getDataSourceId());
			}
		}


		commissionBase.setCreatedate(new Date());
		commissionBase.setDataSourceId(userMT4Account.getDataSourceId());
		commissionBase.setGuid(UUID.randomUUID().toString());
		commissionBase.setHandCommission(new BigDecimal(0.0D));
		commissionBase.setMt4AccountGroup(mt4Users.getGroup());
		commissionBase.setRules(commissionCoefficient.getRuleId());
		commissionBase.setIbId(userRegister.getId()+"");
		logger.info("用户的角色类型websiteUserType:{}",UserAccountTypeEnum.valueOf(userRegister.getWebsiteUserType()).getText());

		//todo commissionBase 设置ibIDType
		if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			commissionBase.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue()+"");
		}else if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
			commissionBase.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue()+"");
		}
		
		commissionBase.setMt4account(userMT4Account.getMt4Account());
		commissionBase.setMt4Commission(new BigDecimal(0.0D));
		commissionBase.setMt4CommissionAgent(new BigDecimal(0.0D));
		commissionBase.setMt4DataSourceType(userMT4Account.getMt4DatasourceType());
		commissionBase.setSpecialId(specialId);

		long pipLong = System.currentTimeMillis();

		if(mode.equals("POINT")){
			//todo 按点值算佣
			commissionBase.setPipCommission(new BigDecimal(pipCommissin(symbol,userMT4Account.getDataSourceId()).doubleValue()*Double.parseDouble(commissionCoefficient.getCoefficient())*(mt4trader.getVolume()/100.0)*porgation));
		}else if(mode.equals("FIXED")){
			//todo 按手数算佣
			commissionBase.setPipCommission(new BigDecimal(Double.parseDouble(commissionCoefficient.getCoefficient())*(mt4trader.getVolume()/100.0)*porgation));
		}
		logger.info("insertIntoDetail ---commissionBase.setPipCommission  pipCommissin() times :{}",System.currentTimeMillis()-pipLong);

		commissionBase.setProfit(new BigDecimal(mt4trader.getProfit()));
		String currency ="";
		logger.info("用户mt4账号的记录userMT4Account:{}",userMT4Account);

		//todo 获取当前账户的币种
		currency = getTradeCurrency(mt4Users.getGroup(),currency);
		if(!("".equals(currency))){

			//todo 账户币种和 usd 的汇率系数
			commissionBase.setRate(RateUtil.getCurrencyRate(currency,"USD",userMT4Account.getDataSourceId()));
		}else{
			commissionBase.setRate(new BigDecimal(1.0D));
		}


		commissionBase.setSettledate(mt4trader.getCloseTime());
		commissionBase.setSource(commissionType);
		commissionBase.setStatus(0+"");
		commissionBase.setSymbol(symbol);
		commissionBase.setVolume(new BigDecimal(mt4trader.getVolume()/100.0));
		commissionBase.setTicket(mt4trader.getTicket()+"");
		commissionBase.setUnitPrice(new BigDecimal(Double.parseDouble(commissionCoefficient.getCoefficient())));
		commissionBase.setUpdatedate(new Date());
		commissionBase.setUserId(userMT4Account.getUid()+"");
		String currency2 ="";
		logger.info("用户mt4账号的记录userMT4Account2:{}",userMT4Account2);

		//todo 上级的用户的 mt4User
		if(null!=mt4Users1){
			currency2 = getTradeCurrency(mt4Users1.getGroup(),currency2);
		}
		if(!("".equals(currency2))){

			//todo 上级的账户和 USD 的交易系数
			commissionBase.setParRate(RateUtil.getCurrencyRate(currency2,"USD",userMT4Account.getDataSourceId()));
		}else{
			commissionBase.setParRate(new BigDecimal(1.0D));
		}

		commissionBase.setSwaps(new BigDecimal(mt4trader.getSwaps()));
		commissionBase.setSettMode(mode);
		commissionBase.setPorgation(new BigDecimal(porgation));
		commissionBase.setTradeCateId(tradingGroupId);
		commissionBaseService.doInsert(commissionBase);
    }
    
    /**
     * 点值返佣的佣金计算的单价
     */
    private BigDecimal pipCommissin(String symbol,int dataSourceId){
    	logger.info("用户交易的货币品种symbol:{}",symbol);
    	Mt4Prices mt4Prices = mt4PricesService.findById(symbol);
    	if(symbol.length()>=6){
    		symbol = symbol.substring(0, 6);
    	}
    	BigDecimal commission = new BigDecimal(100000.0D);
    	BigDecimal contractSize = new BigDecimal(0.0D);
    	List<PipSetting> pipSettingList = pipSettingService.findAll();
    	logger.info("点值计算的方式种类pipSetting:{}",pipSettingList.size());
    	for(PipSetting pipSetting:pipSettingList){
    		if(pipSetting.getTradeVeriaty().indexOf(symbol)!=-1){
    			commission = getPipCommissionsize(symbol,commission);
    			logger.info("点值计算的系数commission:{}，pipSetting的编号Id:{}",commission,pipSetting.getId());
    			if(pipSetting.getId()==1){
    				contractSize = new BigDecimal(0.0001*commission.doubleValue());
    			}else if(pipSetting.getId()==2){
    				contractSize = new BigDecimal(0.01*commission.doubleValue());
    			}else if(pipSetting.getId()==3){
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    				
    			}else if(pipSetting.getId()==4){
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.01*commission.doubleValue()/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==5){
    				mt4Prices = mt4PricesService.findById("USDJPY");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.01*commission.doubleValue()/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==6){
    				mt4Prices = mt4PricesService.findById("USDCAD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==7){
    				mt4Prices = mt4PricesService.findById("USDCHF");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==8){
    				mt4Prices = mt4PricesService.findById("NZDUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==9){
    				mt4Prices = mt4PricesService.findById("AUDUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==10){
    				mt4Prices = mt4PricesService.findById("GBPUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(0.0001*commission.doubleValue()*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}else if(pipSetting.getId()==11){
    				contractSize = new BigDecimal(1);
    			}
    			else if(pipSetting.getId()==12){
    				mt4Prices = mt4PricesService.findById("AUDUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(1*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}
    			else if(pipSetting.getId()==13){
    				mt4Prices = mt4PricesService.findById("USDHKD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(1/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}
    			else if(pipSetting.getId()==14){
    				mt4Prices = mt4PricesService.findById("GBPUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(1*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}
    			else if(pipSetting.getId()==15){
    				mt4Prices = mt4PricesService.findById("USDJPY");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(1/mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}
    			else if(pipSetting.getId()==16){
    				mt4Prices = mt4PricesService.findById("EURUSD");
    				if(mt4Prices.getBid()>0){
    					contractSize = new BigDecimal(1*mt4Prices.getBid());
    				}else{
    					contractSize = new BigDecimal(0.0);
    				}
    			}
    		}
    	}
    	logger.info("该交易记录的点值计算的每点的单价contractSize:{}",contractSize);
    	return contractSize;
    }
    
    /**
     * 佣金计算的系数
     */
    private BigDecimal getPipCommissionsize(String symbol,BigDecimal commission){
    	List<TradeCurrencyDictionary> currencyDictionaries = iTradeCurrencyDictionaryService.findAll();
		for(TradeCurrencyDictionary currencyDictionary : currencyDictionaries){
			if(currencyDictionary.getId()==4) continue;
			if(currencyDictionary.getCurrencyValue().indexOf(symbol)!=-1){
				TradeDictionary tradeDictionary = tradeDictionaryService.findById(currencyDictionary.getTraderId());
				commission = tradeDictionary.getContractSize();
			}
		}
		return commission;
    }
    
    /**
     * 获取MT4账号的币种
     */
	private String getTradeCurrency(String mt4groupId,String currency2){
		if(!("".equals(mt4groupId))) {
			String[] currencys =mt4groupId.split("_");
			currency2 = currencys[mt4groupId.split("_").length-1];
			//logger.info("mt4组的名字:{}",str);
		}
		return currency2;
	}

    private String getTradeCurrency1(UserMT4Account userMT4Account2,Set<String> groupSet,List<DataSourceBean> dataSourceBeanList, DataSourceBean dataSourceBean,String currency2){
    	try{
			Integer.parseInt(userMT4Account2.getMt4GroupId().trim());
		}catch(Exception e){
			if(!(null!=groupSet && groupSet.size()>0)){
				 Map<Integer,Set<String>> map = getGrou(dataSourceBeanList);
				 groupSet = map.get(dataSourceBean.getId());
			}
			for(String str:groupSet){
				logger.info("mt4组的名字:{}",str);
				if(userMT4Account2.getMt4GroupId().equals(str)){

					/*if(dataSourceBean.getMt4LiveIp().equals("0")){
						//currency2 = DLLAgent.getDepositCurrency(userMT4Account2.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4DemoIp());
					}else if(dataSourceBean.getMt4DemoIp().equals("0")){
						try{
							currency2 = DLLAgent.getDepositCurrency(userMT4Account2.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4LiveIp());
						}catch(Exception e1){
							logger.error("====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",new Date(),e1.getLocalizedMessage() + "----" + e1.getMessage());
						}
					}
					break;*/
				}
			}
		}
    	return currency2;
    }
    
    /**
     * 判断用户是否有返佣账号，并调用不同的方法
     */
    private void monthCommission(UserRegister userRegister,String sYesterday,SimpleDateFormat sf,Calendar ctoday,String sToday,Calendar yesterday) throws ParseException, PayException{
    	User user = userService.getByUid(userRegister.getId());
    	logger.info("当前用户的信息user:{}",user);
		if(null!=user){
	        Date date1 = setDateTime(yesterday.getTime(),DEFAULT_DAY_BEGIN);
	        Date date2 = setDateTime(ctoday.getTime(),DEFAULT_DAY_END);
			HashMap<String,Object> params = new HashMap<String,Object>();
			List<UserMT4Account> userMT4AccountList2 = userMT4AccountService.getUserMT4AccountByUserUserStatus(userRegister.getId(), UserMT4StatusEnum.IB_COMMISSION.getValue());
			logger.info("当前用户uid:{}的返佣账号的个数count:{},用户的类型websiteUserType:{}",userRegister.getId(),userMT4AccountList2.size(),UserAccountTypeEnum.valueOf(userRegister.getWebsiteUserType()).getText());
			if(null!=userMT4AccountList2 && userMT4AccountList2.size()>0){
				for(UserMT4Account userMT4Account2:userMT4AccountList2){
					logger.info("IB用户的返佣账号mt4Account:{}",userMT4Account2.getMt4Account());
					insertMonth(params,userRegister, userMT4Account2.getMt4Account(), ctoday,date1,date2, sToday,user, yesterday, sYesterday);
				}
			}else{
				if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
					logger.info("sales 用户的id:{}",userRegister.getId());
					insertMonth(params,userRegister, 0, ctoday,date1,date2, sToday,user, yesterday, sYesterday);
				}
			}
		}
    }
    
    /**
     * 获取该用户的总交易量和总返佣
     */
    public void insertMonth(HashMap<String,Object> params,UserRegister userRegister,int mt4Account,Calendar ctoday,Date date1,Date date2,String sToday,User user,Calendar yesterday,String sYesterday) throws ParseException, PayException{
    	List<CommissionBase> commissionBaseList = new ArrayList<CommissionBase>();
    	//获取改用户在一段时间内佣金计算的记录
    	commissionBaseList = getCommissionMonthByTime(date1,date2,userRegister,mt4Account,commissionBaseList,params);
		logger.info("从start:{}到end:{},用户:{}的返佣账号:{}返佣的个数count:{}",date1,date2,userRegister.getId(),mt4Account,commissionBaseList.size());
		if(null!=commissionBaseList&&commissionBaseList.size()>0){
			BigDecimal amount =new BigDecimal(0.0D);
			BigDecimal volume =new BigDecimal(0.0D);
			//汇总佣金
			amount = getAmountOrVolume(1,commissionBaseList,amount);
			//汇总交易量
			volume = getAmountOrVolume(2,commissionBaseList,volume);
			//插入汇总记录
			insertIntoMonthCommission(ctoday,mt4Account,userRegister,amount,sToday,yesterday,volume);
		}
				
		//根据MT4账号汇总
		commissionAccountCommission(userRegister,sYesterday,yesterday,mt4Account,user,commissionBaseList);
    }
    
    private List<CommissionBase> getCommissionMonthByTime(Date date1,Date date2,UserRegister userRegister,int mt4Account,List<CommissionBase> commissionBaseList,HashMap<String,Object> params){
    	params.put("ibId", userRegister.getId());
		params.put("userMT4Account", mt4Account);
		params.put("start", date1);
		params.put("end", date2);
		commissionBaseList = commissionBaseService.getCommissionBaseByIbId(params);
    	return commissionBaseList;
    }
    
    private BigDecimal getAmountOrVolume(int type,List<CommissionBase> commissionBaseList,BigDecimal big){
    	for(CommissionBase commissionBase:commissionBaseList){
			try{
				if(type==1){
					big=big.add(new BigDecimal(commissionBase.getPipCommission().doubleValue()/commissionBase.getParRate().doubleValue()));
				}else{
					big=big.add(commissionBase.getVolume());
				}
			}catch(Exception e) {
				logger.error("佣金计算异常,异常对象CommissionBase:{},异常信息exception:{}", new Gson().toJson(commissionBase),e.getMessage());
			}
		}
    	return big;
    }
    
    /**
     * 插入记录
     */
    private void insertIntoMonthCommission(Calendar ctoday,int mt4Account,UserRegister userRegister,BigDecimal amount,String sToday,Calendar yesterday,BigDecimal volume){
    	CommissionMonth commissionMonth = new CommissionMonth();
		commissionMonth.setCreatedate(ctoday.getTime());
		commissionMonth.setDepositAmount(new BigDecimal(0));
		commissionMonth.setMt4account(mt4Account); //该IB和内部用户的返佣账号
		commissionMonth.setGuid(UUID.randomUUID().toString());
		commissionMonth.setIbId(userRegister.getId()+"");
		if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			commissionMonth.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue()+"");
		}else if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
			commissionMonth.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue()+"");
		}
		commissionMonth.setDepositAmount(amount);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String syestaday = sf.format(yesterday.getTime());
		commissionMonth.setPaymentDate(syestaday); //改为佣金产生的日期
		//commissionMonth.setPaymentDate(sToday);
		commissionMonth.setStatus(IBCommissionStatusEnum.NO_PAY.getValue());
		commissionMonth.setUpdatedate(ctoday.getTime());
		commissionMonth.setUserId(userRegister.getId()+"");
		commissionMonth.setVolume(volume);
		commissionMonth.setWithdrawAmount(new BigDecimal(0));
		try{
			logger.info("插入数据汇总");
				commissionMonthService.doInsert(commissionMonth);
		}catch(Exception e) {
			logger.error("插入拥挤记录异常,异常对象commissionMonth:{},异常信息exception:{}", new Gson().toJson(commissionMonth), e.getMessage());
		}
    }
    
    private void commissionAccountCommission(UserRegister userRegister,String sYesterday,Calendar yesterday,int mt4Account,User user,List<CommissionBase> commissionBaseList) throws PayException, ParseException{
    	//1.首先是自己账号汇总
		insertMyAccountCommission(userRegister,sYesterday,yesterday,mt4Account);
			
		//2.自己的直接和间接下级的账号汇总
		//2.1 拿到直接和间接下级用户的uid
		List<Integer> userIdList = new ArrayList<Integer>();
		userIdList = getDownUserid(userIdList,user,commissionBaseList);
		
		//2.2 计算
		insertDownUsersCommission(userIdList,user,sYesterday,userRegister,yesterday,mt4Account);
		
    }
    
    private void insertMyAccountCommission(UserRegister userRegister,String sYesterday,Calendar yesterday,int mt4Account) throws PayException, ParseException{
    	List<UserMT4Account> userMT4AccountList = userMT4AccountService.getUserMT4AccountByUid(userRegister.getId());
		if(null!=userMT4AccountList && userMT4AccountList.size()>0){
			for(UserMT4Account userMT4Account2:userMT4AccountList){
				if(null!=userMT4Account2&&userMT4Account2.getMt4Account()>0){
					logger.info("插入数据账号汇总，自己的账号");
					insertINtoAccountCommission(sYesterday,userMT4Account2,userRegister,yesterday,mt4Account);
				}
			}
		}
    }
    
    private List<Integer> getDownUserid(List<Integer> userIdList,User user,List<CommissionBase> commissionBaseList){
    	for(CommissionBase commissionBase:commissionBaseList){
			String ib = commissionBase.getUserId();
			int u2id = user.getUserId();
			if(u2id!=Integer.parseInt(ib)){
				if(null!=userIdList && userIdList.size()>0){
					boolean flag = false;
					for(Integer uid:userIdList){
						if(uid==Integer.parseInt(commissionBase.getUserId())){
							flag = true;
						}
					}
					if(flag){
						continue;
					}else{
						userIdList.add(Integer.parseInt(commissionBase.getUserId()));
					}
				}else{
					userIdList.add(Integer.parseInt(commissionBase.getUserId()));
				}
			}
		}
    	return userIdList;
    }
    
    private void insertDownUsersCommission(List<Integer> userIdList,User user,String sYesterday,UserRegister userRegister,Calendar yesterday,int mt4Account) throws PayException, ParseException{
    	List<UserRegister> userRegisterList2 = userRegisterService.getByIdList(userIdList);
		if(null!=userRegisterList2 && userRegisterList2.size()>0){
			for(UserRegister userRegist: userRegisterList2){
				User u = userService.getByUid(userRegist.getId());
				if(null!=u){
					int zib = u.getUserId();
					int fib = user.getUserId();
					if(zib!=fib){
						List<UserMT4Account> userMt4AccountList = userMT4AccountService.getUserMT4AccountByUid(u.getUserId());
						if(null!=userMt4AccountList && userMt4AccountList.size()>0){
							for(UserMT4Account userMT4Account2:userMt4AccountList){
								logger.info("插入数据账号汇总，自己下面的客户");
								insertINtoAccountCommission(sYesterday,userMT4Account2,userRegister,yesterday,mt4Account);
							}
						}
					}
				}
			}
		}
    }
    
    private void insertINtoAccountCommission(String sYesterday,UserMT4Account userMT4Account,UserRegister userRegister,Calendar yesterday,int mt4Account) throws ParseException{
    	HashMap<String,Object> dateAccount=new HashMap<String,Object>();
        Calendar ctoday = (Calendar)yesterday.clone();
        ctoday.add(Calendar.DAY_OF_MONTH, 1);
        Date date1 = setDateTime(yesterday.getTime(),DEFAULT_DAY_BEGIN); 
        Date date2 = setDateTime(ctoday.getTime(),DEFAULT_DAY_END);
        dateAccount.put("start", date1);
		dateAccount.put("end", date2);
		dateAccount.put("dataSourceId", userMT4Account.getDataSourceId());
		dateAccount.put("ibId", userRegister.getId());
		dateAccount.put("mt4Account",userMT4Account.getMt4Account());
		dateAccount.put("userMT4Account", mt4Account);
		List<CommissionBase> commissionBaseList2 = commissionBaseService.getCommissionBaseByIbId(dateAccount);
		BigDecimal amountAccount = new BigDecimal(0.0D);
		BigDecimal commissionAccount = new BigDecimal(0.0D);
		BigDecimal volumeAccount = new BigDecimal(0.0D);
		if(null!=commissionBaseList2 && commissionBaseList2.size()>0){
		for(CommissionBase commissionBase:commissionBaseList2){
			amountAccount= amountAccount.add(commissionBase.getPipCommission());
			volumeAccount = volumeAccount.add(commissionBase.getVolume());
			commissionAccount = commissionAccount.add(new BigDecimal(commissionBase.getPipCommission().doubleValue()*commissionBase.getRate().doubleValue()));
			}
		}
		//插入数据
		insertIntoAccountCommission(userMT4Account,commissionAccount,amountAccount,userRegister,yesterday,volumeAccount);
    }
    
    /**
     * 插入根据MT4账号汇总的数据
     * @param userMT4Account
     * @param commissionAccount
     * @param amountAccount
     * @param userRegister
     * @param yesterday
     * @param volumeAccount
     */
    private void insertIntoAccountCommission(UserMT4Account userMT4Account,BigDecimal commissionAccount,BigDecimal amountAccount,UserRegister userRegister,Calendar yesterday,BigDecimal volumeAccount){
    	CommissionAccountDetail commissionAccountDetail = new CommissionAccountDetail();
		commissionAccountDetail.setComment("");
		commissionAccountDetail.setDataSourceId(userMT4Account.getDataSourceId());
		commissionAccountDetail.setMt4DataSourceType(userMT4Account.getMt4DatasourceType());
		commissionAccountDetail.setCurrencyCommission(commissionAccount);
		commissionAccountDetail.setCommission(amountAccount);
		commissionAccountDetail.setCreatedate(new Date());
		commissionAccountDetail.setGuid(UUID.randomUUID().toString());
		if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			commissionAccountDetail.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue()+"");
		}else if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()){
			commissionAccountDetail.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue()+"");
		}
		commissionAccountDetail.setIbId(userRegister.getId()+"");
		commissionAccountDetail.setMt4account(userMT4Account.getMt4Account());
		commissionAccountDetail.setSettledate(yesterday.getTime());
		commissionAccountDetail.setSource("");
		commissionAccountDetail.setStatus(0+"");
		commissionAccountDetail.setSymbol("");
		commissionAccountDetail.setUpdatedate(new Date());
		commissionAccountDetail.setUserId(userRegister.getId()+"");
		commissionAccountDetail.setVolume(volumeAccount);
		commissionAccountDetail.setBaseId(0+"");
		commissionAccountDetailService.doInsert(commissionAccountDetail);
    }

	@Override
	public void commissionDay() throws ParseException, PayException {
		Calendar date=Calendar.getInstance();
		Calendar today2 = (Calendar)date.clone();
		today2.add(Calendar.DAY_OF_MONTH, -1);

		//todo 计算佣金
		calCommission(today2.getTime(), date.getTime());

		//todo 维护month 表
		calculateMonthlyIbCommission(date);
	}

	/**
	 * 佣金计算方法
	 */
	private void commissionmethod(String mode,User user,Mt4Trades trades,UserMT4Account userMT4Account,String symbol,UserCommissionConf userCommissionConf,Set<String> groupSet,int tradingGroupId ,CommissionRules commissionGroup,UserRegister userRegister2,List<DataSourceBean> dataSourceBeanList){

		//todo 根据 ruleid 查询指定的规则明细(规则链)
		List<CommisionCoefficient> commisionCoefficientList = commisionCoefficientService.getCommisionCoefficientByRuleId(Integer.parseInt(userCommissionConf.getCommRuleId()));
		logger.info("该用户的规则的大小count:{},规则的编号rulesId:{}",commisionCoefficientList,userCommissionConf.getCommRuleId());

		//todo 遍历规则明细,计算佣金维护 base表
		if(null!=commisionCoefficientList && commisionCoefficientList.size()>0){

			//todo 获取交易帐号 对应的 MT4user 信息
			Mt4Users mt4Users= mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMT4Account.getDataSourceId());
			for(CommisionCoefficient commisionCoefficient:commisionCoefficientList){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("specialUid", commisionCoefficient.getUid());
				params.put("commissionType", 1);

				//todo 判断该返佣的 用户 有没有例外
				List<CommissionSpecial> commissionSpecialList = commissionSpecialService.getCommissionSpecialByuserid(params);
				if(null!=commissionSpecialList && commissionSpecialList.size()>0){
					//todo 有例外的处理方法
					specialCommissionMethod(dataSourceBeanList,groupSet,commissionGroup,tradingGroupId,commisionCoefficient,symbol,mode,user,trades,commissionSpecialList,userMT4Account);
				}else{
					//todo 没有添加例外
					long insertLong = System.currentTimeMillis();

					//todo 往 base 表插入数据
					insertIntoDetail(trades,userMT4Account,symbol,commisionCoefficient,tradingGroupId,commissionGroup.getUserdefine1(),groupSet,mode,dataSourceBeanList,null,1.0,0+"",mt4Users);
					logger.info("insertIntoDetail ---time :{}ms",System.currentTimeMillis()-insertLong);
				}
			}
		}
	}
	
	/**
	 * 点值手数例外佣金的处理方法
	 */
	private void specialCommissionMethod(List<DataSourceBean> dataSourceBeanList,Set<String> groupSet,CommissionRules commissionGroup,int tradingGroupId,CommisionCoefficient commisionCoefficient,String symbol,String mode,User user,Mt4Trades trades,List<CommissionSpecial> commissionSpecialList,UserMT4Account userMT4Account){
		boolean flag = false;
		double porgation = 0.0;
		String specialId ="";
		Map<String,Object> params = new HashMap<String,Object>();
		for(CommissionSpecial commissionSpecial : commissionSpecialList){
			params = isSpecialCommissionRules(dataSourceBeanList,groupSet,commissionGroup,tradingGroupId,commisionCoefficient,symbol,mode,user,trades,commissionSpecial,flag,userMT4Account);
			porgation += (double)params.get("porgation");
			specialId += commissionSpecial.getId();
		}
		Mt4Users mt4Users= mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMT4Account.getDataSourceId());
		insertIntoDetail(trades,userMT4Account,symbol,commisionCoefficient,tradingGroupId,commissionGroup.getUserdefine1(),groupSet,mode,dataSourceBeanList,null,1-porgation,specialId,mt4Users);
	}
	
	/**
	 * 附加返佣的例外的处理方法
	 */
	private void specialCommissionMethod(UserMT4Account userMt4Account,Mt4Trades trades,String sToday,CommisionCoefficient commisionCoefficient,List<CommissionSpecial> commissionSpecialList){
		Map<String,Object> params = new HashMap<String,Object>();
		BigDecimal profitAmount = new BigDecimal(trades.getProfit());
		boolean flag = false;
		String specialId = "";
		double porgation =0.0;
		for(CommissionSpecial commissionSpecial : commissionSpecialList){
			params = isSpecialCommissionRules(commissionSpecial,userMt4Account,trades,sToday,commisionCoefficient,commissionSpecialList,flag);
			porgation += (double)params.get("porgation");
			specialId += commissionSpecial.getId();
		}
		Mt4Users mt4Users = mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMt4Account.getDataSourceId());
		insertProfit(userMt4Account,trades,sToday,commisionCoefficient,null,1-porgation,specialId,mt4Users);
	}
	
	/**
	 * 点值手数例外的返佣范围的处理
	 */
	private Map<String,Object> isSpecialCommissionRules(CommissionSpecial commissionSpecial,UserMT4Account userMt4Account,Mt4Trades trades,String sToday,CommisionCoefficient commisionCoefficient,List<CommissionSpecial> commissionSpecialList,boolean flag){
		Map<String,Object> params = new HashMap<String,Object>();
//		BigDecimal profitAmount =new BigDecimal(trades.getProfit());
		if(commissionSpecial.getRangeType()==1){
			List<Integer> uidList = new ArrayList<Integer>();
			UserRegister userRegister = userRegisterService.findById(commissionSpecial.getCustomerId());
			//uidList = getDownUserId(uidList,userRegister,commissionSpecial.getAgentAccount());
			List<UserMT4Account> userM4AcList = userMT4AccountService.getUserMT4AccountByAgentAccount(commissionSpecial.getAgentAccount(), userRegister.getMt4DataSourceId());
			uidList = getUidList(userM4AcList,userRegister,uidList);
			boolean flagz = getExist(userMt4Account.getUid(),uidList);
			if(flagz){
				params = insertIntoSpecialCommission(commissionSpecial,userMt4Account,trades,sToday,commisionCoefficient,commissionSpecialList,flag);
			}else{
				params.put("porgation", 0.0);
			}
		}else if(commissionSpecial.getRangeType()==2){
			//是否存在例外的返佣规则
			if((int)commissionSpecial.getCustomerId()==userMt4Account.getUid()){
				params = insertIntoSpecialCommission(commissionSpecial,userMt4Account,trades,sToday,commisionCoefficient,commissionSpecialList,flag);
			}else{
				params.put("porgation", 0.0);
			}
		}else{
			params.put("porgation", 0.0);
		}
		return params;
	}
	
	/**
	 * 附加例外的返佣范围的处理
	 */
	private Map<String,Object> isSpecialCommissionRules(List<DataSourceBean> dataSourceBeanList,Set<String> groupSet,CommissionRules commissionGroup,int tradingGroupId,CommisionCoefficient commisionCoefficient,String symbol,String mode,User user,Mt4Trades trades,CommissionSpecial commissionSpecial,boolean flag,UserMT4Account userMT4Account){
		Map<String,Object> params = new HashMap<String,Object>();
		if(commissionSpecial.getRangeType()==1){
			List<Integer> uidList = new ArrayList<Integer>();
			UserRegister userRegister = userRegisterService.findById(commissionSpecial.getCustomerId());
			List<UserMT4Account> userM4AcList = userMT4AccountService.getUserMT4AccountByAgentAccount(commissionSpecial.getAgentAccount(), userRegister.getMt4DataSourceId());
			//uidList = getDownUserId(uidList,userRegister,commissionSpecial.getAgentAccount());
			uidList = getUidList(userM4AcList,userRegister,uidList);
			boolean flagz = getExist(userMT4Account.getUid(),uidList);
			if(flagz){
				params = insertIntoSpecialCommission(dataSourceBeanList,groupSet,commissionGroup,tradingGroupId,commisionCoefficient,symbol,mode,user,trades,commissionSpecial,flag,userMT4Account);
			}else{
				params.put("porgation", 0.0);
				params.put("flag", false);
			}
		}else if(commissionSpecial.getRangeType()==2){
			//是否存在例外的返佣规则
			if((int)commissionSpecial.getCustomerId()==userMT4Account.getUid()){
				params = insertIntoSpecialCommission(dataSourceBeanList,groupSet,commissionGroup,tradingGroupId,commisionCoefficient,symbol,mode,user,trades,commissionSpecial,flag,userMT4Account);
			}else{
				params.put("porgation", 0.0);
				params.put("flag", false);
			}
		}else{
			params.put("porgation", 0.0);
		}
		return params;
	}
	
	private List<Integer> getUidList(List<UserMT4Account> userM4AcList,UserRegister userRegister,List<Integer> uidList){
		if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
			for(UserMT4Account userMT4Account:userM4AcList){
				if((int)userMT4Account.getUid()==userRegister.getId() && userMT4Account.getUserStaus()==UserMT4StatusEnum.IB_COMMISSION.getValue()){
					uidList = getDownUserId(uidList,userRegister,userMT4Account.getMt4Account());
				}
			}
		}else if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.PERSONAL_ACCOUNT.getValue()){
			uidList.add(userRegister.getId());
		}
		return uidList;
	}
	
	/**
	 * 附加例外的佣金计算
	 */
	private Map<String,Object> insertIntoSpecialCommission(CommissionSpecial commissionSpecial,UserMT4Account userMt4Account,Mt4Trades trades,String sToday,CommisionCoefficient commisionCoefficient,List<CommissionSpecial> commissionSpecialList,boolean flag){
		Map<String,Object> params = new HashMap<String,Object>();
		List<CommissionSpecialRules> commissionSpecialRulesList = commissionSpecialRulesService.getCommissionSpecialRulesById(commissionSpecial.getId());
		double porgation = 0.0;
		if(null!=commissionSpecialRulesList && commissionSpecialRulesList.size()>0){
			Mt4Users mt4Users = mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMt4Account.getDataSourceId());
			for(CommissionSpecialRules commissionSpecialRules : commissionSpecialRulesList){
				porgation += (commissionSpecialRules.getProportion().doubleValue()/100.0);
				insertProfit(userMt4Account,trades,sToday,commisionCoefficient,commissionSpecialRules,commissionSpecialRules.getProportion().doubleValue()/100.0,commissionSpecialRules.getSpecialId()+"",mt4Users);
			}
			flag =true;
		}
		params.put("porgation", porgation);
		return params;
	}
	
	/**
	 * 点值手数例外的佣金计算
	 */
	private Map<String,Object> insertIntoSpecialCommission(List<DataSourceBean> dataSourceBeanList,Set<String> groupSet,CommissionRules commissionGroup,int tradingGroupId,CommisionCoefficient commisionCoefficient,String symbol,String mode,User user,Mt4Trades trades,CommissionSpecial commissionSpecial,boolean flag,UserMT4Account userMT4Account){
		Map<String,Object> params = new HashMap<String,Object>();
		List<CommissionSpecialRules> commissionSpecialRulesList = commissionSpecialRulesService.getCommissionSpecialRulesById(commissionSpecial.getId());
		double porgaton = 0.0;
		Mt4Users mt4Users= mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMT4Account.getDataSourceId());
		if(null!=commissionSpecialRulesList && commissionSpecialRulesList.size()>0){
			for(CommissionSpecialRules commissionSpecialRules : commissionSpecialRulesList){
				porgaton+=(commissionSpecialRules.getProportion().doubleValue()/100.0);
				insertIntoDetail(trades,userMT4Account,symbol,commisionCoefficient,tradingGroupId,commissionGroup.getUserdefine1(),groupSet,mode,dataSourceBeanList,commissionSpecialRules,(commissionSpecialRules.getProportion().doubleValue()/100.0),commissionSpecialRules.getSpecialId()+"",mt4Users);
			}
			flag =true;
		}
		params.put("flag", flag);
		params.put("porgation", porgaton);
		return params;
	}
	
	/**
	 * 获取用户的某个返佣账号的所有下级（直接和间接）
	 */
	private List<Integer> getDownUserId(List<Integer> uidList,UserRegister userRegister,int mt4Account){
		if(null!=userRegister){
			if(userRegister.getWebsiteUserType()==UserAccountTypeEnum.IB_ACCOUNT.getValue()){
				uidList.add(userRegister.getId());
				List<UserMT4Account> userMt4AccountList = userMT4AccountService.getUserMT4AccountByAgentAccount(mt4Account, userRegister.getMt4DataSourceId());
				if(null!=userMt4AccountList && userMt4AccountList.size()>0){
					for(UserMT4Account userMT4Account:userMt4AccountList){
						UserRegister userRegister2 = userRegisterService.findById(userMT4Account.getUid());
						boolean flag = getExist(userRegister2.getId(),uidList);
						if(!flag){
							uidList = getDownUserId(uidList,userRegister2,userMT4Account.getMt4Account());
						}
					}
				}
			}else{
				uidList.add(userRegister.getId());
			}
		}
		return uidList;
	}
	
	private boolean getExist(int uid,List<Integer> userRegisterList){
    	boolean flag = false;
    	for(Integer userid:userRegisterList){
    		if((int)userid==uid){
    			flag = true;
    		}
    	}
    	return flag;
    }
	
	/**
	 * 附加返佣的佣金计算
	 */
	private void profitCommissionMethod(UserCommissionConf userCommissionConf,UserMT4Account userMT4Account,Mt4Trades trades,String sToday){
		List<CommisionCoefficient> commisionCoefficientList = commisionCoefficientService.getCommisionCoefficientByRuleId(Integer.parseInt(userCommissionConf.getCommRuleId()));
		logger.info("该用户的规则的大小count:{},规则的编号rulesId:{}",commisionCoefficientList,userCommissionConf.getCommRuleId());
		if(null!=commisionCoefficientList && commisionCoefficientList.size()>0){
			Mt4Users mt4Users = mt4UsersService.getMt4UsersByMt4Account(trades.getLogin(),userMT4Account.getDataSourceId());
			for(CommisionCoefficient commisionCoefficient:commisionCoefficientList){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("specialUid", commisionCoefficient.getUid());
				params.put("commissionType", RulesCommissionTypeEnum.ADDITION_COMMISSION.getValue());
				List<CommissionSpecial> commissionSpecialList = commissionSpecialService.getCommissionSpecialByuserid(params);
				if(null!=commissionSpecialList && commissionSpecialList.size()>0){ //有添加例外
					specialCommissionMethod(userMT4Account,trades,sToday,commisionCoefficient,commissionSpecialList);
				}else{ // 没有添加例外
					insertProfit(userMT4Account,trades,sToday,commisionCoefficient,null,1.0,0+"",mt4Users);
				}
			}
		}
	}
}
