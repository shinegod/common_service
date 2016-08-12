package com.fx.crm.cron.comm.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.MT4.util.DLLAgent;
import com.fx.MT4.util.MT4GroupUtil;
import com.fx.admin.model.Admin;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.bonus.enums.IBCommissionStatusEnum;
import com.fx.bonus.enums.IsInnerSalesEnum;
import com.fx.bonus.model.CommisionCoefficient;
import com.fx.bonus.model.CommissionRules;
import com.fx.bonus.model.PipSetting;
import com.fx.bonus.model.ProfitCommission;
import com.fx.bonus.model.TradeCurrencyDictionary;
import com.fx.bonus.model.TradeDictionary;
import com.fx.bonus.model.TradingGroup;
import com.fx.bonus.service.ICommisionCoefficientService;
import com.fx.bonus.service.ICommissionRulesService;
import com.fx.bonus.service.IPipSettingService;
import com.fx.bonus.service.IProfitCommissionService;
import com.fx.bonus.service.ITradeCurrencyDictionaryService;
import com.fx.bonus.service.ITradeDictionaryService;
import com.fx.bonus.service.ITradingGroupService;
import com.fx.bonus.service.impl.ProfitCommissionServiceImpl;
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
import com.fx.mt4TradeRecord.service.IMt4PricesService;
import com.fx.mt4TradeRecord.service.IMt4TradesService;
import com.fx.multidatasource.DataSourceContextHolder;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.payment.util.UserMT4StatusEnum;
import com.fx.user.enums.UserAccountTypeEnum;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.user.service.IUserService;
import com.fx.util.CacheMgr;
import com.fx.util.RateUtil;
import com.google.gson.Gson;

/**
 * Created by bei2love@gmail.com on 15/6/1.
 */
@Service
public class CommCalculateServiceImpl implements ICommCalculateService {

    private static final Logger logger = LoggerFactory.getLogger(CommCalculateServiceImpl.class);

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String DEFAUT_DATE_SPLIT_CHAR = "-";

    private static final String DEFAULT_TIME_SPLIT_CHAR = ":";

    private static final String DEFAULT_DATE_TIME_SPLIT_CHAR = " ";

    private static final String DEFAULT_DAY_BEGIN = "00:00:00";

    private static final String DEFAULT_DAY_END = "23:59:59";

    private static final String COMMISSION_BASE_STATUS_NEW = "0";

    private static final String COMMISSION_ACCOUNT_DETAIL_STATUS_NEW = "0";

    private static final String USER_IB_TYPE_SALES = "0";

    private static final String USER_IB_TYPE_IB = "1";

    private static final String COMM_RULE_PREFIX_USER = "user:";

    private static final String COMM_RULE_PREFIX_ADMIN = "admin:";

    private static final int DEFAULT_BATCH_INSERT_SIZE = 50;

    @Autowired
    private IMt4TradesService mt4TradesService;

    @Autowired
    private ICommissionBaseService commissionBaseService;

    @Autowired
    private ICommissionAccountDetailService commissionAccountDetailService;

//    @Autowired
//    private ICommissionRulesService commissionRulesService;

    @Autowired
    private IUserCommissionConfService userCommissionConfService;

    @Autowired
    private ITradingGroupService tradingGroupService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPipSettingService pipSettingService;

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

    /**
     * 计算佣金
     * @param begin 佣金计算的开始时间，为空默认取当天的0时
     * @param end  佣金计算的结束时间，为空默认为当天的24时
     * @throws ParseException
     */
    @Override
    public void calCommission(Date begin, Date end) throws ParseException {


//    	IMt4PricesService mt4PricesService2 = SpringUtils.getBean(IMt4PricesService.class);
//    	double dd = RateUtil.getCurrencyRate("AUD","CAD",6).doubleValue();
        setDefaultDate(begin, end);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = " 00:00:01";
        String over = " 00:00:00";
        String str1 = sf.format(begin);
        String str2 = sf.format(end);
        Date date1 = sf2.parse(str1 + start);
        Date date2 = sf2.parse(str2 + over);


        //多数据源之后加
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("sourceWebSideType", DataSourceTypeEnum.PERSONAL_ACCOUNT.getValue());
        params.put("databaseType", "MySQL");
        List<DataSourceBean> dataSourceBeanList = dataSourceService.getDataSourceBeanByParams(params);
        logger.info("多数据源的个数count:{}", dataSourceBeanList.size());
        Map<Integer, Set<String>> map = getGrou(dataSourceBeanList);
        logger.info("mt4账号组获取，mt4组的大小,count:{}", map.size());
        if (null != dataSourceBeanList && dataSourceBeanList.size() > 0) {
            for (DataSourceBean dataSourceBean : dataSourceBeanList) {
                logger.info("当前数据源的名字:{}", dataSourceBean.getDataName());
                // 1.查询MySQL获取时间段内的数据
                Set<String> groupSet = null;
                try {
//        			groupSet = MT4GroupUtil.getGroupWangList(false, dataSourceBean);
                    groupSet = map.get(dataSourceBean.getId());
                    logger.info("该数据源下的mt4账号组，数据源名:{},mt4组的个数:{}", dataSourceBean.getDataName(), groupSet.size());
                } catch (Exception e) {
                    logger.error(
                            "====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",
                            new Date(),
                            e.getLocalizedMessage() + "----" + e.getMessage());
                }

                List<Mt4Trades> tradesList = mt4TradesService.getMt4TradesByCloseTime(date1, date2, dataSourceBean.getId());
                logger.info("从start:{}到end:{}这段时间产生的交易记录的个数count:{}", date1, date2, tradesList.size());
                for (Mt4Trades trades : tradesList) {
                    logger.info("交易记录的mt4账号login:{}", trades.getLogin());
                    UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Accounts(trades.getLogin(), dataSourceBean.getId());
                    logger.info("对应的数据库中的账号记录是:{}", userMT4Account);
                    if (null != userMT4Account) {
                        logger.info("对应的数据库中的账号是:{}", userMT4Account.getMt4Account());
                        UserRegister userRegister = userRegisterService.findById(userMT4Account.getUid());
                        User user = userService.getByUid(userRegister.getId());
                        logger.info("该MT4账号对应的用户注册信息:{}，用户信息:{}", userRegister, user);
                        if (null != userRegister && null != user) {
                            //如果该用户不是Ib的话，获取该用户的上级IB,如果是IB,直接拿到他自己。
                            UserRegister userRegister2 = null;
                            logger.info("用户的网站类型是:{}", UserAccountTypeEnum.valueOf(userRegister.getWebsiteUserType()).getText());
                            logger.info("该MT4账号的上级:{}，该用户的上级:{}", userMT4Account.getIbId(), userRegister.getIs_ibId());
                            if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                                if (userMT4Account.getIbId() == userRegister.getIs_ibId()) {
                                    userRegister2 = userRegister;
                                    logger.info("上级IB的编号uid:{}", userRegister2);
                                } else {
                                    userRegister2 = userRegisterService.findById(userMT4Account.getIbId());
                                    logger.info("上级IB的编号uid:{}", userRegister2);
                                }
                            } else if (userRegister.getIs_ibId() > 0) {
                                userRegister2 = userRegisterService.findById(userMT4Account.getIbId());
                                logger.info("上级IB:{}", userRegister2);
                            }

                            //获取该数据源下的所有品种组
                            List<TradingGroup> tradingGroupList = tradingGroupService.getTradeGroupByDataSource(userMT4Account.getDataSourceId());
                            int tradingGroupId = -1;
                            String symbol = "";
                            logger.info("用户交易的货币对，用户:{},货币对:{}，交易记录的id:{}", userMT4Account.getUid(), trades.getSymbol(), trades.getLogin());
                            //判断这条交易记录的交易商品属于哪个品种组
                            for (TradingGroup tradingGroup : tradingGroupList) {
//                    			String symbol2 = trades.getSymbol().substring(0, 6);
                                if (tradingGroup.getTradingCategory().indexOf(symbol) != -1) {
                                    tradingGroupId = tradingGroup.getId();
                                    symbol = trades.getSymbol();
                                    logger.info("交易记录交易的货币对属于哪个品种组，交易货币对:{},交易品种组Id:{}", symbol, tradingGroupId);
                                    break;
                                }
                            }
                            logger.info("交易货币对:{},交易品种组Id:{}", symbol, tradingGroupId);
                            //如果获取的上级IB或者该用户不存在，跳过
                            if (null != userRegister2) {
                                //获取该IB的所用佣金计算的规则
                                Map<String, Object> param = new HashMap<String, Object>();
                                param.put("userId", userRegister2.getId());
                                param.put("type", "1");
                                param.put("agentAccount", userMT4Account.getAgentMt4Account() + "");
                                List<UserCommissionConf> userCommissionConfList = userCommissionConfService.findByUidAccount(param);
                                logger.info("该用户的直属IB对应的规则，uid:{},返佣账号:{},对应规则的个数:{}", userRegister2.getId(), userMT4Account.getAgentMt4Account(), userCommissionConfList.size());
                                //判断使用那个具体的规则计算
                                for (UserCommissionConf userCommissionConf : userCommissionConfList) {
                                    CommissionRules commissionGroup = commisionGroupService.findById(userCommissionConf.getCommRuleId());
                                    logger.info("返佣的规则:{}", commissionGroup);
                                    if (null != commissionGroup && (commissionGroup.getUserdefine1().equals("1")) && commissionGroup.getStatus() == 0 && commissionGroup.getDataSourceId() == userMT4Account.getDataSourceId() && tradingGroupId == commissionGroup.getTradingGroupId()) {
                                        //按点值计算   附加返佣  推荐返佣
                                        logger.info("返佣规则的结算方式settleMode:{}", commissionGroup.getSettleMode());
                                        if (commissionGroup.getSettleMode().equals("POINT") || commissionGroup.getCommissionType().equals("NETINCOME") || commissionGroup.getCommissionType().equals("NETPOSITION") || commissionGroup.getCommissionType().equals("FIXEDINCOME") || commissionGroup.getCommissionType().equals("EARNINGSINTO") || commissionGroup.getCommissionType().equals("ACCOUNTREWARD")) {
                                            //获取具体的返佣点值的记录
                                            //该用户是Ib
                                            commissionmethod(commissionGroup.getSettleMode(), user, trades, userMT4Account, symbol, userCommissionConf, groupSet, tradingGroupId, commissionGroup, userRegister2, dataSourceBeanList);

                                        } else if (commissionGroup.getSettleMode().equals("FIXED")) { //手数返佣
                                            commissionmethod("FIXED", user, trades, userMT4Account, symbol, userCommissionConf, groupSet, tradingGroupId, commissionGroup, userRegister2, dataSourceBeanList);
                                        } else if (commissionGroup.getSettleMode().equals("PERCENTAGE")) {  //百分比返佣

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                //附加佣金计算
                List<Mt4Trades> profitTradesList = mt4TradesService.getMt4TradesByCloseTimeGroup(date1, date2, dataSourceBean.getId());
                profitCommission(dataSourceBeanList, dataSourceBean, groupSet, profitTradesList, date1);
            }


        }

//        calculateMonthlyIbCommission();
    }

    /**
     * 当前月份佣金计算
     * @param today 当前时间，用于计算起始时间
     * @throws ParseException
     * @throws PayException
     */
    @Override
    public void calculateMonthlyIbCommission(Calendar today) throws ParseException, PayException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar yesterday = (Calendar) today.clone();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        Calendar ctoday = (Calendar) today.clone();
        String sToday = sf.format(ctoday.getTime());
        String sYesterday = sf.format(yesterday.getTime());
        List<UserRegister> userRegisterList = userRegisterService.getByWebsiteUserTypess(UserAccountTypeEnum.IB_ACCOUNT.getValue());
        logger.info("网站IB用户的个数count:{}", userRegisterList.size());
        for (UserRegister userRegister : userRegisterList) {
            logger.info("当前IB用户的编号uid:{},现在的时间time:{}", userRegister.getId(), sToday);
            monthCommission(userRegister, sYesterday, sf, ctoday, sToday, yesterday);
        }
        List<UserRegister> salesUserRegister = userRegisterService.getByWebsiteUserTypess(UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue());
        logger.info("网站sales用户的个数count:{}", salesUserRegister.size());
        if (null != salesUserRegister && salesUserRegister.size() > 0) {
            for (UserRegister salesUserRegi : salesUserRegister) {
                logger.info("当前IB用户的编号uid:{},现在的时间time:{}", salesUserRegi.getId(), sToday);
                monthCommission(salesUserRegi, sYesterday, sf, ctoday, sToday, yesterday);
            }
        }
    }

    //目前不用 2016-05-26
    private void profitCommission(List<DataSourceBean> dataSourceBeanList, DataSourceBean dataSourceBean, Set<String> groupSet, List<Mt4Trades> tradesList, Date date1) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//    	Calendar date=Calendar.getInstance();
//		Calendar ctoday=(Calendar) date.clone();
//		ctoday.add(Calendar.DAY_OF_MONTH,-1);
        String sToday = sf.format(date1);
        if (null != tradesList && tradesList.size() > 0) {
            logger.info("time:{},today tradings number :{}", new Date(), tradesList.size());
            for (Mt4Trades mt4Trades : tradesList) {
                logger.info("当前用户的mt4Account:{}", mt4Trades.getLogin());
                UserMT4Account userMt4Account = userMT4AccountService.getUserMT4AccountByMt4Accounts(mt4Trades.getLogin(), dataSourceBean.getId());
                logger.info("该mt4Account对应的mt4记录userMT4Account:{}", userMt4Account);
                if (null != userMt4Account && userMt4Account.getMt4Account() > 0) {
                    logger.info("当前的mt4账号:{}", userMt4Account.getMt4Account());
                    UserRegister userRegister = userRegisterService.findById(userMt4Account.getUid());
                    logger.info("当前用户对应的用户注册信息userRegister:{}", userRegister);
                    User user = null;
                    if (null != userRegister) {
                        user = userService.getByUid(userRegister.getId());
                    }
                    logger.info("当前用户的信息user:{}", user);
                    if (null != user) {
                        //找出该mt4Account的上级
                        UserRegister userRegister2 = null;
                        logger.info("用户的网站类型是:{}", UserAccountTypeEnum.valueOf(userRegister.getWebsiteUserType()).getText());
                        logger.info("该MT4账号的上级:{}，该用户的上级:{}", userMt4Account.getIbId(), userRegister.getIs_ibId());
                        if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                            if (userMt4Account.getIbId() == userRegister.getIs_ibId()) {
                                userRegister2 = userRegister;
                                logger.info("上级IB的编号uid:{}", userRegister2);
                            } else {
                                userRegister2 = userRegisterService.findById(userMt4Account.getIbId());
                                logger.info("上级IB的编号uid:{}", userRegister2);
                            }
                        } else if (userRegister.getIs_ibId() > 0) {
                            userRegister2 = userRegisterService.findById(userMt4Account.getIbId());
                            logger.info("上级IB:{}", userRegister2);
                        }
                        List<TradingGroup> tradingGroupList = tradingGroupService.getTradeGroupByDataSource(userMt4Account.getDataSourceId());
                        int tradingGroupId = -1;
                        String symbol = "";
                        logger.info("用户交易的货币对，用户:{},货币对:{}，交易记录的id:{}", userMt4Account.getUid(), mt4Trades.getSymbol(), mt4Trades.getLogin());
                        //判断这条交易记录的交易商品属于哪个品种组
                        for (TradingGroup tradingGroup : tradingGroupList) {
//                			String symbol2 = trades.getSymbol().substring(0, 6);
                            if (tradingGroup.getTradingCategory().indexOf(symbol) != -1) {
                                tradingGroupId = tradingGroup.getId();
                                symbol = mt4Trades.getSymbol();
                                logger.info("交易记录交易的货币对属于哪个品种组，交易货币对:{},交易品种组Id:{}", symbol, tradingGroupId);
                                break;
                            }
                        }
                        logger.info("交易货币对:{},交易品种组Id:{}", symbol, tradingGroupId);
                        //如果获取的上级IB或者该用户不存在，跳过
                        if (null != userRegister2) {
                            //获取该IB的所用佣金计算的规则
                            Map<String, Object> param = new HashMap<String, Object>();
                            param.put("userId", userRegister2.getId());
                            param.put("type", "2");
                            param.put("agentAccount", userMt4Account.getAgentMt4Account() + "");
                            List<UserCommissionConf> userCommissionConfList = userCommissionConfService.findByUidAccount(param);
                            logger.info("该用户的直属IB对应的规则，uid:{},返佣账号:{},对应规则的个数:{}", userRegister2.getId(), userMt4Account.getAgentMt4Account(), userCommissionConfList.size());
                            //判断使用那个具体的规则计算
                            for (UserCommissionConf userCommissionConf : userCommissionConfList) {
                                CommissionRules commissionGroup = commisionGroupService.findById(userCommissionConf.getCommRuleId());
                                logger.info("返佣的规则:{}", commissionGroup);
                                if (null != commissionGroup && commissionGroup.getUserdefine1().equals("2") && commissionGroup.getStatus() == 0 && commissionGroup.getDataSourceId() == userMt4Account.getDataSourceId() && tradingGroupId == commissionGroup.getTradingGroupId()) {
                                    // 附加返佣
                                    logger.info("返佣规则的结算方式settleMode:{}", commissionGroup.getSettleMode());
                                    if (commissionGroup.getSettleMode().equals("PERCENTAGE")) {  //百分比返佣
                                        List<CommisionCoefficient> commisionCoefficientList = commisionCoefficientService.getCommisionCoefficientByRuleId(Integer.parseInt(userCommissionConf.getCommRuleId()));
                                        logger.info("该用户的规则的大小count:{},规则的编号rulesId:{}", commisionCoefficientList, userCommissionConf.getCommRuleId());
                                        if (null != commisionCoefficientList && commisionCoefficientList.size() > 0) {
                                            Integer roleId = user.getRoleId();
                                            logger.info("用户角色对应的Id:{}", roleId);
                                            if (null != roleId && user.getRoleId() > 0) {
                                                Admin admin = null;
                                                User user1 = user;
                                                profitCommissionMethod(admin, user1, userMt4Account, commisionCoefficientList, mt4Trades, dataSourceBeanList, sToday);
                                            } else {
                                                logger.info("该用户的上级的角色 类别websiteUserType:{}", UserAccountTypeEnum.valueOf(userRegister2.getWebsiteUserType()).getText());
                                                if (userRegister2.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                                                    User user1 = userService.getByUid(userRegister2.getId());
                                                    Admin admin = null;
                                                    profitCommissionMethod(admin, user1, userMt4Account, commisionCoefficientList, mt4Trades, dataSourceBeanList, sToday);
                                                } else if (userRegister2.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                                                    Admin admin = adminService.findByUserId(userRegister2.getId());
                                                    Admin admin1 = admin;
                                                    int agentAccount = 0;
                                                    if (null != admin) {
                                                        while (true) {
                                                            UserRegister userRegister3 = userRegisterService.findById(admin.getUserId());

                                                            if ((int) admin.getId() == (int) admin1.getId()) {
                                                                if (!(userMt4Account.getIbId() == admin.getUserId())) {
                                                                    userRegister3 = userRegisterService.findById(userMt4Account.getIbId());
                                                                }
                                                                agentAccount = userMt4Account.getAgentAccount();
                                                                logger.info("用户的直接IB的返佣账号的agentAccount:{}", agentAccount);
                                                            }
                                                            logger.info("admin userRegister3:{}", userRegister3);
                                                            if (null != userRegister3) {
                                                                logger.info("用户userRegister3的id:{}", userRegister3.getId());
                                                                for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                                                                    if (commissionCoefficient.getRoleId() == admin.getRoleId()) {
                                                                        logger.info("用户上级的roleId:{},规则设置的值coefficient:{}", admin.getRoleId(), commissionCoefficient.getCoefficient());
                                                                        insertProfit(userMt4Account, userRegister3, new BigDecimal(mt4Trades.getProfit()), sToday, agentAccount, commissionCoefficient.getCoefficient());
                                                                    }
                                                                }
                                                                UserRegister userRegister4 = null;
                                                                if (agentAccount != 0) {
                                                                    UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMt4Account.getDataSourceId());
                                                                    agentAccount = userMT4Account2.getAgentMt4Account();
                                                                    logger.info("用户mt4Account:{}的上级返佣账号是agentAccount:{}", userMT4Account2, agentAccount);
                                                                    userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                                                                } else {
                                                                    userRegister4 = userRegisterService.findById(userRegister3.getSuperior_id());
                                                                }
                                                                logger.info("admin userRegister4:{}", userRegister4);
                                                                if (null != userRegister4) {
                                                                    if (userRegister4.getSuperior_id() > 0) {
                                                                        admin = adminService.findByUserId(userRegister4.getId());
                                                                    } else {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    break;
                                                                }
                                                            } else {
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //目前不用 2016-05-26
    private void profitCommissionMethod(Admin admin, User user1, UserMT4Account userMT4Account, List<CommisionCoefficient> commisionCoefficientList, Mt4Trades mt4trader, List<DataSourceBean> dataSourceBeanList, String sToday) {

        User user = user1;
        UserRegister userRegister5 = userRegisterService.findById(user.getUserId());
        int agentAccount = 0;
        if (null != user1) {
            while (true) {
                UserRegister userRegister3 = new UserRegister();
                if ((int) userMT4Account.getUid() == (int) user.getUserId()) {
                    userRegister3 = userRegisterService.findById(user1.getUserId());
                    if (userRegister5.getId() == userRegister3.getId()) {
                        if (!(userMT4Account.getIbId() == userRegister3.getIs_ibId())) {
                            userRegister3 = userRegisterService.findById(userMT4Account.getIbId());
                        }
                        agentAccount = userMT4Account.getAgentMt4Account();
                        logger.info("用户第一次的mt4Account的返佣账号是agentAccount:{},mt4Account的上级id:{}", agentAccount, userMT4Account.getIbId());
                    }
                    logger.info("用户的注册信息userRegister3:{}", userRegister3);
                } else {
                    if ((int) user.getUserId() == (int) user1.getUserId()) {
                        if (!(userMT4Account.getIbId() == user1.getUserId())) {
                            userRegister3 = userRegisterService.findById(userMT4Account.getIbId());
                        } else {
                            userRegister3 = userRegisterService.findById(user1.getUserId());
                        }
                        agentAccount = userMT4Account.getAgentMt4Account();
                        logger.info("普通用户第一次的mt4Account的返佣账号是agentAccount:{},mt4Account的上级id:{}", agentAccount, userMT4Account.getIbId());
                    } else {
                        userRegister3 = userRegisterService.findById(user1.getUserId());
                    }
                    logger.info("用户的注册信息userRegister3:{}", userRegister3);
                }

                if (null != userRegister3) {
                    for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                        if (commissionCoefficient.getRoleId() == user1.getRoleId()) {
                            //插入详情
                            logger.info("用户的上级角色Id:{},用户返佣设置的值coefficient:{}", user1.getRoleId(), commissionCoefficient.getCoefficient());
                            insertProfit(userMT4Account, userRegister3, new BigDecimal(mt4trader.getProfit()), sToday, agentAccount, commissionCoefficient.getCoefficient());
                        }
                    }
                    //查询下级IB或者销售
                    UserRegister userRegister4 = null;
                    if (agentAccount != 0) {
                        UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
                        agentAccount = userMT4Account2.getAgentMt4Account();
                        logger.info("用户的mt4Account:{},的返佣账号agentAccount:{}", userMT4Account2.getMt4Account(), agentAccount);
                        userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                    } else {
                        userRegister4 = userRegisterService.findById(userRegister3.getIs_ibId());
                    }
                    logger.info("user的，userRegister4:{}", userRegister4);
                    if (null != userRegister4) {
                        if (userRegister4.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                            user1 = userService.getByUid(userRegister4.getId());
                        } else if (userRegister4.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                            admin = adminService.findByUserId(userRegister4.getId());
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }

                } else {
                    break;
                }
            }
        }

        if (null != admin) {
            while (true) {
                UserRegister userRegister3 = userRegisterService.findById(admin.getUserId());
                for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                    if (commissionCoefficient.getRoleId() == admin.getRoleId()) {
                        //插入详情
                        insertProfit(userMT4Account, userRegister3, new BigDecimal(mt4trader.getProfit()), sToday, agentAccount, commissionCoefficient.getCoefficient());
                    }
                }
                UserRegister userRegister4 = null;
                if (agentAccount != 0) {
                    UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
                    agentAccount = userMT4Account2.getAgentMt4Account();
                    userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                } else {
                    userRegister4 = userRegisterService.findById(userRegister3.getSuperior_id());
                }
                if (null != userRegister4) {
                    if (userRegister4.getSuperior_id() > 0) {
                        admin = adminService.findByUserId(userRegister4.getId());
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
    //目前不用 2016-05-26
    private void insertProfit(UserMT4Account userMt4Account, UserRegister userRegister, BigDecimal profitAmount, String sToday, int agentAccount, String unitPrice) {
        ProfitCommission profitCommission = new ProfitCommission();

        profitCommission.setCreatedate(new Date());
        profitCommission.setMt4account(userMt4Account.getMt4Account());
        profitCommission.setGuid(UUID.randomUUID().toString());
        profitCommission.setIbId(userRegister.getId() + "");
        if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
            profitCommission.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue() + "");
        } else if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
            profitCommission.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue() + "");
        }
        profitCommission.setPaymentDate(sToday);
        profitCommission.setStatus(IBCommissionStatusEnum.NO_PAY.getValue());
        profitCommission.setUpdatedate(new Date());
        profitCommission.setUserId(userMt4Account.getUid() + "");
        profitCommission.setProfitAmount(profitAmount);
        profitCommission.setDataSourceId(userRegister.getMt4DataSourceId());
        profitCommission.setAgentaccount(agentAccount);
        profitCommission.setUnitPrice(new BigDecimal(Double.parseDouble(unitPrice)));
        profitCommissionService.doInsert(profitCommission);
    }

    private Map<Integer, Set<String>> getGrou(List<DataSourceBean> dataSourceBeanList) {

        Map<Integer, Set<String>> groupMap = CacheMgr.get("group");
        if (groupMap == null) {
            groupMap = getfromdll(dataSourceBeanList);
            CacheMgr.put("group", groupMap);
        }
        return groupMap;
    }

    private Map<Integer, Set<String>> getfromdll(List<DataSourceBean> dataSourceBeanList) {
        Map<Integer, Set<String>> groupMap = new HashMap<Integer, Set<String>>();
        if (null != dataSourceBeanList && dataSourceBeanList.size() > 0) {
            for (DataSourceBean dataSourceBean : dataSourceBeanList) {
                Set<String> groupSet = null;
                try {
                    groupSet = MT4GroupUtil.getGroupWangList(false, dataSourceBean);
                    groupMap.put(dataSourceBean.getId(), new HashSet<String>(groupSet));
                } catch (Exception e) {
                    logger.error(
                            "====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",
                            new Date(),
                            e.getLocalizedMessage() + "----" + e.getMessage());
                }

            }
        }
        return groupMap;
    }


    private CommissionAccountDetail getCommissionAccountDetailByBase(CommissionBase commBase) {
        CommissionAccountDetail commActDet = new CommissionAccountDetail();
        Date now = new Date(System.currentTimeMillis());
        commActDet.setGuid(UUID.randomUUID().toString());
        commActDet.setUpdatedate(now);
        commActDet.setCreatedate(now);
        commActDet.setVolume(commBase.getVolume());
        commActDet.setSymbol(commBase.getSymbol());
        commActDet.setBaseId(commBase.getGuid());
        commActDet.setComment(commBase.getComment());
        commActDet.setMt4account(commBase.getMt4account());
        commActDet.setSettledate(now);
        commActDet.setSource(commBase.getSource());
        commActDet.setStatus(COMMISSION_ACCOUNT_DETAIL_STATUS_NEW);
        return commActDet;
    }

    /**
     * 数据异常检查
     * @param t
     * @param userMT4Account
     * @param user
     * @param userIB
     * @param sale
     * @param isIBUser
     * @return
     */
    private boolean checkDataSupport(Mt4Trades t, UserMT4Account userMT4Account, User user, User userIB, Admin sale, Boolean isIBUser) {
        //判断是否需要计算佣金
        //没有ib，并且没有销售不计算反应
        if (userMT4Account == null) {
            logger.info("未找到交易记录对应的UserMT4Account数据，交易记录对应的mt4账户为 {}", t.getLogin());
            return false;
        }
        if (user == null) {
            logger.info("未找到交易记录对应的User数据，交易记录对应的mt4账户和账户组: {},{}", new Object[]{userMT4Account.getMt4Account(), userMT4Account.getMt4GroupId()});
            return false;
        }
        //找到当前用户的上级，并计算佣金
        //如果上级IB和销售都为空，不对当前记录计算佣金
        if (user.getIb_Id() == 0 && user.getSell_Id() == 0) {
            logger.info("当前用户无上级和销售，不进行佣金计算。用户编号:{}", user.getUserId());
            return false;
        }
        //上级
        if (user.getIb_Id() != 0) {
            userIB = CacheMgr.getUserCache().get(user.getIb_Id());
            isIBUser = true;
        } else if (user.getSell_Id() != 0) {
            sale = CacheMgr.getAdminCache().get(user.getSell_Id());
            isIBUser = false;
        } else {
            logger.info("无法获取用户上级，不进行佣金计算。用户编号:{}", user.getUserId());
            return false;
        }
        return true;
    }

    /**
     * 获取点值返佣的基数（根据点值计算规则计算得到）
     *
     * @param symbol
     * @return
     */
    private BigDecimal getPipHandCommission(String symbol) {
        //TODO: 当前没有对点值进行设置计算，返回默认值 0
        return BigDecimal.valueOf(0);
    }

    /**
     * 获取手数返佣基数
     *
     * @return
     */
    private BigDecimal getHandCommission() {
        //TODO:确认手数返佣的基数
        return BigDecimal.valueOf(1);
    }

    /**
     * 通过交易记录获取佣金基数
     *
     * @param trades
     * @return
     */
    private CommissionBase getCommBaseFromMT4Trades(Date end, Mt4Trades trades) {
//        Date now = new Date(System.currentTimeMillis());
        CommissionBase base = new CommissionBase();
        base.setGuid(UUID.randomUUID().toString());
        base.setStatus(COMMISSION_BASE_STATUS_NEW);
        base.setCloseTime(trades.getCloseTime());
        base.setComment(trades.getComment());
        base.setCreatedate(end);
        base.setMt4account(trades.getLogin());
        base.setMt4Commission(BigDecimal.valueOf(trades.getCommission()));
        base.setMt4CommissionAgent(BigDecimal.valueOf(trades.getCommissionAgent()));
        base.setProfit(BigDecimal.valueOf(trades.getProfit()));
        //TODO: 确认基础数据源的分类方案
        base.setSource("0");
        base.setSwaps(BigDecimal.valueOf(trades.getSwaps()));
        base.setSymbol(trades.getSymbol());
        base.setTicket(trades.getTicket().toString());
        base.setVolume(BigDecimal.valueOf(trades.getVolume()));
        base.setUpdatedate(end);
        return base;
    }

    /**
     * 设置默认时间，默认取一天的数据
     *
     * @param begin
     * @param end
     */
    private void setDefaultDate(Date begin, Date end) {
        try {
            if (begin == null) {
                StringBuffer tdyStr = getDayString().append(DEFAULT_DAY_BEGIN);
                begin = DEFAULT_DATE_FORMAT.parse(tdyStr.toString());
            }
            if (end == null) {
                StringBuffer tdyStr = getDayString().append(DEFAULT_DAY_END);
                end = DEFAULT_DATE_FORMAT.parse(tdyStr.toString());
            }
        } catch (ParseException e) {
            logger.info("设置默认时间出错，请检查佣金计算设置的时间参数.");
            logger.error("计算佣金基数出现异常，{}", e);
        }
    }


    /**
     * 获取当天时间的年月日默认字符串
     *
     * @return
     */
    private static StringBuffer getDayString() {
        Calendar today = Calendar.getInstance();
        StringBuffer todayStr = new StringBuffer(20);
        todayStr.append(today.get(Calendar.YEAR)).append(DEFAUT_DATE_SPLIT_CHAR);
        todayStr.append(today.get(Calendar.MONTH)).append(DEFAUT_DATE_SPLIT_CHAR);
        todayStr.append(today.get(Calendar.DAY_OF_MONTH)).append(DEFAULT_DATE_TIME_SPLIT_CHAR);
//        todayStr.append(today.get(Calendar.HOUR_OF_DAY)).append(DEFAULT_TIME_SPLIT_CHAR);
        return todayStr;
    }

    @Override
    public void calCommissionAccount(Date begin, Date end) {

    }

    private void insertDayDetial(Admin admin, User user1, List<CommisionCoefficient> commisionCoefficientList, Mt4Trades mt4trader, String symbol, UserMT4Account userMT4Account, int tradingGroupId, String commissionType, Set<String> groupSet, String mode, List<DataSourceBean> dataSourceBeanList) {
        User user = user1;
        UserRegister userRegister5 = userRegisterService.findById(user.getUserId());
        int agentAccount = 0;
        if (null != user1) {
            while (true) {
                UserRegister userRegister3 = new UserRegister();
                if ((int) userMT4Account.getUid() == (int) user.getUserId()) {
                    userRegister3 = userRegisterService.findById(user1.getUserId());
                    if (userRegister5.getId() == userRegister3.getId()) {
                        if (!(userMT4Account.getIbId() == userRegister3.getIs_ibId())) {
                            userRegister3 = userRegisterService.findById(userMT4Account.getIbId());
                        }
                        agentAccount = userMT4Account.getAgentMt4Account();
                        logger.info("用户第一次的mt4Account的返佣账号是agentAccount:{},mt4Account的上级id:{}", agentAccount, userMT4Account.getIbId());
                    }
                    logger.info("用户的注册信息userRegister3:{}", userRegister3);
                } else {
                    if ((int) user.getUserId() == (int) user1.getUserId()) {
                        if (!(userMT4Account.getIbId() == user1.getUserId())) {
                            userRegister3 = userRegisterService.findById(userMT4Account.getIbId());
                        } else {
                            userRegister3 = userRegisterService.findById(user1.getUserId());
                        }
                        agentAccount = userMT4Account.getAgentMt4Account();
                        logger.info("普通用户第一次的mt4Account的返佣账号是agentAccount:{},mt4Account的上级id:{}", agentAccount, userMT4Account.getIbId());
                    } else {
                        userRegister3 = userRegisterService.findById(user1.getUserId());
                    }
                    logger.info("用户的注册信息userRegister3:{}", userRegister3);
                }

                if (null != userRegister3) {
//					Role role = roleService.findById(user1.getRoleId());
                    for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                        if (commissionCoefficient.getRoleId() == user1.getRoleId()) {
                            //插入详情
                            logger.info("用户的上级角色Id:{},用户返佣设置的值coefficient:{}", user1.getRoleId(), commissionCoefficient.getCoefficient());
                            insertIntoDetail(mt4trader, userMT4Account, userRegister3, symbol, commissionCoefficient, tradingGroupId, commissionType, groupSet, mode, agentAccount, dataSourceBeanList);
                        }
                    }
                    //查询下级IB或者销售
                    UserRegister userRegister4 = null;
                    if (agentAccount != 0) {
                        UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
                        agentAccount = userMT4Account2.getAgentMt4Account();
                        logger.info("用户的mt4Account:{},的返佣账号agentAccount:{}", userMT4Account2.getMt4Account(), agentAccount);
                        userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                    } else {
                        userRegister4 = userRegisterService.findById(userRegister3.getIs_ibId());
                    }
                    logger.info("user的，userRegister4:{}", userRegister4);
                    if (null != userRegister4) {
                        if (userRegister4.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                            user1 = userService.getByUid(userRegister4.getId());
                        } else if (userRegister4.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                            admin = adminService.findByUserId(userRegister4.getId());
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }

                } else {
                    break;
                }
            }
        }

        if (null != admin) {
            while (true) {
//				Role role = roleService.findById(admin.getRoleId());
                UserRegister userRegister3 = userRegisterService.findById(admin.getUserId());
                for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                    if (commissionCoefficient.getRoleId() == admin.getRoleId()) {
                        //插入详情
                        insertIntoDetail(mt4trader, userMT4Account, userRegister3, symbol, commissionCoefficient, tradingGroupId, commissionType, groupSet, mode, 0, dataSourceBeanList);
                    }
                }
                UserRegister userRegister4 = null;
                if (agentAccount != 0) {
                    UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
                    agentAccount = userMT4Account2.getAgentMt4Account();
                    userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                } else {
                    userRegister4 = userRegisterService.findById(userRegister3.getSuperior_id());
                }
                if (null != userRegister4) {
                    if (userRegister4.getSuperior_id() > 0) {
                        admin = adminService.findByUserId(userRegister4.getId());
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 返佣明细表
     * @param mt4trader 获取mt4的CloseTime(闭仓时间),Volume,Profit
     * @param userMT4Account 获取Mt4Account(用户MT4账号),DataSourceId,Mt4DatasourceType
     * @param userRegister3  为返佣账号设置上级ib_id，ibIdType的值
     * @param symbol 货币对
     * @param commissionCoefficient 返佣规则明细表(tb_commision_coefficient)
     * @param tradingGroupId 品种组ID
     * @param commissionType
     * @param groupSet mt4组
     * @param mode 佣金计算的方式
     * @param agentAccount 返佣账号
     * @param dataSourceBeanList
     */
    private void insertIntoDetail(Mt4Trades mt4trader, UserMT4Account userMT4Account, UserRegister userRegister3, String symbol, CommisionCoefficient commissionCoefficient, int tradingGroupId, String commissionType, Set<String> groupSet, String mode, int agentAccount, List<DataSourceBean> dataSourceBeanList) {
        logger.info("用户的Mt4Account:{}的所属数据源dataSource:{}", userMT4Account.getMt4Account(), userMT4Account.getDataSourceId());
        DataSourceBean dataSourceBean = DataSourceContextHolder.getDATASOURCEBEAN_MAP().get(userMT4Account.getDataSourceId());
        CommissionBase commissionBase = new CommissionBase();
        commissionBase.setCloseTime(mt4trader.getCloseTime());
        commissionBase.setComment("");
        commissionBase.setCreatedate(new Date());
        commissionBase.setDataSourceId(userMT4Account.getDataSourceId());
        commissionBase.setGuid(UUID.randomUUID().toString());
        commissionBase.setHandCommission(new BigDecimal(0.0D));
        commissionBase.setIbId(userRegister3.getId() + "");
        logger.info("用户的角色类型websiteUserType:{}", UserAccountTypeEnum.valueOf(userRegister3.getWebsiteUserType()).getText());
        if (userRegister3.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
            commissionBase.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue() + "");
        } else if (userRegister3.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
            commissionBase.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue() + "");
        }
        commissionBase.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue() + "");
        commissionBase.setAgentAccount(agentAccount);
        commissionBase.setMt4account(userMT4Account.getMt4Account());
        commissionBase.setMt4Commission(new BigDecimal(0.0D));
        commissionBase.setMt4CommissionAgent(new BigDecimal(0.0D));
        commissionBase.setMt4DataSourceType(userMT4Account.getMt4DatasourceType());
        logger.info("佣金计算的方式settleMode:{}", mode);
        if (mode.equals("POINT")) {//按点值算佣
            commissionBase.setPipCommission(new BigDecimal(pipCommissin(symbol, userMT4Account.getDataSourceId()).doubleValue() * Double.parseDouble(commissionCoefficient.getCoefficient()) * (mt4trader.getVolume() / 100.0)));
        } else if (mode.equals("FIXED")) {//按手数算佣
            commissionBase.setPipCommission(new BigDecimal(Double.parseDouble(commissionCoefficient.getCoefficient()) * (mt4trader.getVolume() / 100.0)));
        }
        commissionBase.setProfit(new BigDecimal(mt4trader.getProfit()));
        String currency = "";
        logger.info("用户mt4账号的记录userMT4Account:{}", userMT4Account);
        try {
            logger.info("用户mt4账号的记录userMT4Account的组名mt4groupName:{}", userMT4Account.getMt4GroupId());
            Integer.parseInt(userMT4Account.getMt4GroupId());
        } catch (Exception e) {

            logger.info("mt4组:{}", userMT4Account.getMt4GroupId());
            if (!(null != groupSet && groupSet.size() > 0)) {
                Map<Integer, Set<String>> map = getGrou(dataSourceBeanList);
                groupSet = map.get(dataSourceBean.getId());
            }
            logger.info("mt4组的大小count:{}", groupSet.size());
            for (String str : groupSet) {
                logger.info("mt4组的名字:{}", str);
                if (userMT4Account.getMt4GroupId().equals(str)) {
                    if (dataSourceBean.getMt4LiveIp().equals("0")) {
                        //currency = DLLAgent.getDepositCurrency(userMT4Account.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4DemoIp());
                    } else if (dataSourceBean.getMt4DemoIp().equals("0")) {
                        try {
                            currency = DLLAgent.getDepositCurrency(userMT4Account.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4LiveIp());
                        } catch (Exception e1) {
                            logger.error(
                                    "====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",
                                    new Date(),
                                    e1.getLocalizedMessage() + "----" + e1.getMessage());
                        }

                    }
                    break;
                }
            }
        }

        if (!("".equals(currency))) {
            commissionBase.setRate(RateUtil.getCurrencyRate(currency, "USD", userMT4Account.getDataSourceId()));
        } else {
            commissionBase.setRate(new BigDecimal(1.0D));
        }
        commissionBase.setSettledate(mt4trader.getCloseTime());
        commissionBase.setSource(commissionType);
        commissionBase.setStatus(0 + "");
        commissionBase.setSymbol(symbol);
        commissionBase.setVolume(new BigDecimal(mt4trader.getVolume() / 100.0));
        commissionBase.setTicket(mt4trader.getTicket() + "");
        commissionBase.setUnitPrice(new BigDecimal(Double.parseDouble(commissionCoefficient.getCoefficient())));
        commissionBase.setUpdatedate(new Date());
        commissionBase.setUserId(userMT4Account.getUid() + "");
//		UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByUidUserStatus(userRegister3.getId(), UserMT4StatusEnum.IB_COMMISSION.getValue());
        UserMT4Account userMT4Account2 = null;
        if (agentAccount != 0) {
            userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
        }
        String currency2 = "";
        logger.info("用户mt4账号的记录userMT4Account2:{}", userMT4Account2);
        if (null != userMT4Account2) {
            try {
                Integer.parseInt(userMT4Account2.getMt4GroupId().trim());
            } catch (Exception e) {

                logger.info("mt4组:{}", userMT4Account2.getMt4GroupId());
                if (!(null != groupSet && groupSet.size() > 0)) {
                    Map<Integer, Set<String>> map = getGrou(dataSourceBeanList);
                    groupSet = map.get(dataSourceBean.getId());
                }
                logger.info("mt4组的大小count:{}", groupSet.size());
                for (String str : groupSet) {
                    logger.info("mt4组的名字:{}", str);
                    if (userMT4Account2.getMt4GroupId().equals(str)) {
                        if (dataSourceBean.getMt4LiveIp().equals("0")) {
                            //currency2 = DLLAgent.getDepositCurrency(userMT4Account2.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4DemoIp());
                        } else if (dataSourceBean.getMt4DemoIp().equals("0")) {
                            try {
                                currency2 = DLLAgent.getDepositCurrency(userMT4Account2.getMt4GroupId(), dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), dataSourceBean.getMt4LiveIp());
                            } catch (Exception e1) {
                                logger.error(
                                        "====CommCalculateServiceImpl  commission date:{},佣金计算失败，meg:{}",
                                        new Date(),
                                        e1.getLocalizedMessage() + "----" + e1.getMessage());
                            }

                        }
                        break;
                    }
                }
            }
        }

        if (!("".equals(currency2))) {
//			mt4Prices2 = mt4PricesService.findById(currency2+"USD");
            commissionBase.setParRate(RateUtil.getCurrencyRate(currency2, "USD", userMT4Account.getDataSourceId()));
        } else {
            commissionBase.setParRate(new BigDecimal(1.0D));
        }
        commissionBase.setSwaps(new BigDecimal(mt4trader.getSwaps()));
        commissionBase.setSettMode(mode);
        commissionBase.setTradeCateId(tradingGroupId);
        commissionBaseService.doInsert(commissionBase);
    }

    /**
     * 点值计算的每点的单价公式
     * @param symbol  计算货币
     * @param dataSourceId  数据来源
     * @return
     */
    private BigDecimal pipCommissin(String symbol, int dataSourceId) {
        logger.info("用户交易的货币品种symbol:{}", symbol);
        Mt4Prices mt4Prices = mt4PricesService.findById(symbol);
        if (symbol.length() >= 6) {
            symbol = symbol.substring(0, 6);
        }
        BigDecimal commission = new BigDecimal(100000.0D);
        BigDecimal contractSize = new BigDecimal(0.0D);
        //pipSettingList 操作的表(tb_pip_setting:[直盘usd,交叉盘:没有usd])
        List<PipSetting> pipSettingList = pipSettingService.findAll();
        logger.info("点值计算的方式种类pipSetting:{}", pipSettingList.size());
        for (PipSetting pipSetting : pipSettingList) {
            if (pipSetting.getTradeVeriaty().indexOf(symbol) != -1) {
                //TradeCurrencyDictionary 操作的表(tb_trade_currency_dictionary 1:白银,2:黄金,3:原油,4:外汇)
                List<TradeCurrencyDictionary> currencyDictionaries = iTradeCurrencyDictionaryService.findAll();
                for (TradeCurrencyDictionary currencyDictionary : currencyDictionaries) {
                    if (currencyDictionary.getId() == 4) continue;
                    if (currencyDictionary.getCurrencyValue().indexOf(symbol) != -1) {
                        //TradeDictionary 操作的表(tb_trade_dictionary 根据traderId获取对应的美元转换汇率[此处请慎重询问Alan Or Baker])
                        TradeDictionary tradeDictionary = tradeDictionaryService.findById(currencyDictionary.getTraderId());
                        commission = tradeDictionary.getContractSize();
                    }
                }
                logger.info("点值计算的系数commission:{}，pipSetting的编号Id:{}", commission, pipSetting.getId());
                //计算公式参考tb_pip_setting表的rule_expression字段
                if (pipSetting.getId() == 1) {
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue());
                } else if (pipSetting.getId() == 2) {
                    contractSize = new BigDecimal(0.01 * commission.doubleValue());
                } else if (pipSetting.getId() == 3) {

                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() / mt4Prices.getBid());
                } else if (pipSetting.getId() == 4) {
                    contractSize = new BigDecimal(0.01 * commission.doubleValue() / mt4Prices.getBid());
                } else if (pipSetting.getId() == 5) {
                    mt4Prices = mt4PricesService.findById("USDJPY");

                    contractSize = new BigDecimal(0.01 * commission.doubleValue() / mt4Prices.getBid());
                } else if (pipSetting.getId() == 6) {
                    mt4Prices = mt4PricesService.findById("USDCAD");
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() / mt4Prices.getBid());
                } else if (pipSetting.getId() == 7) {
                    mt4Prices = mt4PricesService.findById("USDCHF");
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() / mt4Prices.getBid());
                } else if (pipSetting.getId() == 8) {
                    mt4Prices = mt4PricesService.findById("NZDUSD");
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() * mt4Prices.getBid());
                } else if (pipSetting.getId() == 9) {
                    mt4Prices = mt4PricesService.findById("AUDUSD");
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() * mt4Prices.getBid());
                } else if (pipSetting.getId() == 10) {
                    mt4Prices = mt4PricesService.findById("GBPUSD");
                    contractSize = new BigDecimal(0.0001 * commission.doubleValue() * mt4Prices.getBid());
                } else if (pipSetting.getId() == 11) {
                    contractSize = new BigDecimal(0.10);
                }
            }
        }
        logger.info("该交易记录的点值计算的每点的单价contractSize:{}", contractSize);
        return contractSize;
    }

    /**
     * 佣金汇总
     * @param userRegister 汇总的用户信息
     * @param sYesterday
     * @param sf
     * @param ctoday
     * @param sToday
     * @param yesterday
     * @throws ParseException
     * @throws PayException
     */
    private void monthCommission(UserRegister userRegister, String sYesterday, SimpleDateFormat sf, Calendar ctoday, String sToday, Calendar yesterday) throws ParseException, PayException {
        User user = userService.getByUid(userRegister.getId());
        logger.info("当前用户的信息user:{}", user);
        if (null != user) {
//			String updateTime = user.getCreateTime();
//			Date update = sf.parse(updateTime);
//			String updateTime2 = sf.format(update);

//			if(updateTime2.compareTo(sYesterday)<0){
            SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start = " 00:00:01";
            String over = " 00:00:00";
            String str1 = sf.format(yesterday.getTime());
            String str2 = sf.format(ctoday.getTime());
            Date date1 = sf2.parse(str1 + start);
            Date date2 = sf2.parse(str2 + over);
            HashMap<String, Object> params = new HashMap<String, Object>();
            List<UserMT4Account> userMT4AccountList2 = userMT4AccountService.getUserMT4AccountByUserUserStatus(userRegister.getId(), UserMT4StatusEnum.IB_COMMISSION.getValue());
            logger.info("当前用户uid:{}的返佣账号的个数count:{},用户的类型websiteUserType:{}", userRegister.getId(), userMT4AccountList2.size(), UserAccountTypeEnum.valueOf(userRegister.getWebsiteUserType()).getText());
            if (null != userMT4AccountList2 && userMT4AccountList2.size() > 0) {
//                //外部用户返佣汇总计算
                for (UserMT4Account userMT4Account2 : userMT4AccountList2) {
                    logger.info("IB用户的返佣账号mt4Account:{}", userMT4Account2.getMt4Account());
                    insertMonth(params, userRegister, userMT4Account2.getMt4Account(), ctoday, date1, date2, sToday, user, yesterday, sYesterday);
                }
            } else {
                //内部用户返佣汇总
                if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                    logger.info("sales 用户的id:{}", userRegister.getId());
                    insertMonth(params, userRegister, 0, ctoday, date1, date2, sToday, user, yesterday, sYesterday);
                }
            }
        }
    }

    /**
     * 佣金汇总计算         编辑的表(tb_IB_commission_month)
     * @param params       查询条件 ibId:用户ID,userMT4Account:用户MT4账号,start:开始时间,end:结束时间
     * @param userRegister 汇总的用户实体信息
     * @param mt4Account   用户的返佣账号
     * @param ctoday       汇总记录修改时间
     * @param date1        开始时间
     * @param date2        结束时间
     * @param sToday
     * @param user
     * @param yesterday    支付时间
     * @param sYesterday
     * @throws ParseException
     * @throws PayException
     */
    public void insertMonth(HashMap<String, Object> params, UserRegister userRegister, int mt4Account, Calendar ctoday, Date date1, Date date2, String sToday, User user, Calendar yesterday, String sYesterday) throws ParseException, PayException {
        params.put("ibId", userRegister.getId());
        params.put("userMT4Account", mt4Account);
        params.put("start", date1);
        params.put("end", date2);
        List<CommissionBase> commissionBaseList = commissionBaseService.getCommissionBaseByIbId(params);
        logger.info("从start:{}到end:{},用户:{}的返佣账号:{}返佣的个数count:{}", date1, date2, userRegister.getId(), mt4Account, commissionBaseList.size());
        if (null != commissionBaseList && commissionBaseList.size() > 0) {
            BigDecimal amount = new BigDecimal(0.0D);
            BigDecimal volume = new BigDecimal(0.0D);
            for (CommissionBase commissionBase : commissionBaseList) {
                try {
                    amount = amount.add(new BigDecimal(commissionBase.getPipCommission().doubleValue() / commissionBase.getParRate().doubleValue()));
                    volume = volume.add(commissionBase.getVolume());
                } catch (Exception e) {
                    logger.error("佣金计算异常,异常对象CommissionBase:{},异常信息exception:{}", new Gson().toJson(commissionBase), e.getMessage());
                }
            }
            CommissionMonth commissionMonth = new CommissionMonth();
            commissionMonth.setCreatedate(ctoday.getTime());
            commissionMonth.setDepositAmount(new BigDecimal(0));
            commissionMonth.setMt4account(mt4Account); //该IB和内部用户的返佣账号
            commissionMonth.setGuid(UUID.randomUUID().toString());
            commissionMonth.setIbId(userRegister.getId() + "");
            if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                commissionMonth.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue() + "");
            } else if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                commissionMonth.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue() + "");
            }

            commissionMonth.setDepositAmount(amount);

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String syestaday = sf.format(yesterday);
            commissionMonth.setPaymentDate(syestaday); //改为佣金产生的日期
            commissionMonth.setStatus(IBCommissionStatusEnum.NO_PAY.getValue());
            commissionMonth.setUpdatedate(ctoday.getTime());
            commissionMonth.setUserId(userRegister.getId() + "");
            commissionMonth.setVolume(volume);
            commissionMonth.setWithdrawAmount(new BigDecimal(0));
            try {
                logger.info("插入数据汇总");
                commissionMonthService.doInsert(commissionMonth);
            } catch (Exception e) {
                logger.error("插入拥挤记录异常,异常对象commissionMonth:{},异常信息exception:{}", new Gson().toJson(commissionMonth), e.getMessage());
            }

        }

        //根据MT4账号汇总

        List<UserMT4Account> userMT4AccountList = userMT4AccountService.getUserMT4AccountByUid(userRegister.getId());
        if (null != userMT4AccountList && userMT4AccountList.size() > 0) {
            for (UserMT4Account userMT4Account2 : userMT4AccountList) {
                if (null != userMT4Account2 && userMT4Account2.getMt4Account() > 0) {
                    logger.info("插入数据账号汇总，自己的账号");
                    insertINtoAccountCommission(sYesterday, userMT4Account2, userRegister, yesterday, mt4Account);
                }
            }
        }


        List<Integer> userIdList = new ArrayList<Integer>();
        for (CommissionBase commissionBase : commissionBaseList) {
            String ib = commissionBase.getUserId();
            int u2id = user.getUserId();
            if (u2id != Integer.parseInt(ib)) {
                if (null != userIdList && userIdList.size() > 0) {
                    boolean flag = false;
                    for (Integer uid : userIdList) {
                        if (uid == Integer.parseInt(commissionBase.getUserId())) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        continue;
                    } else {
                        userIdList.add(Integer.parseInt(commissionBase.getUserId()));
                    }
                } else {
                    userIdList.add(Integer.parseInt(commissionBase.getUserId()));
                }
            }
        }

        List<UserRegister> userRegisterList2 = userRegisterService.getByIdList(userIdList);
        if (null != userRegisterList2 && userRegisterList2.size() > 0) {
            for (UserRegister userRegist : userRegisterList2) {
                User u = userService.getByUid(userRegist.getId());
                if (null != u) {
                    int zib = u.getUserId();
                    int fib = user.getUserId();
                    if (zib != fib) {
                        List<UserMT4Account> userMt4AccountList = userMT4AccountService.getUserMT4AccountByUid(u.getUserId());
                        if (null != userMt4AccountList && userMt4AccountList.size() > 0) {
                            for (UserMT4Account userMT4Account2 : userMt4AccountList) {
                                logger.info("插入数据账号汇总，自己下面的客户");
                                insertINtoAccountCommission(sYesterday, userMT4Account2, userRegister, yesterday, mt4Account);
                            }
                        }
                    }
                }
            }
        }
        //}
    }

    private void insertINtoAccountCommission(String sYesterday, UserMT4Account userMT4Account, UserRegister userRegister, Calendar yesterday, int mt4Account) throws ParseException {
        HashMap<String, Object> dateAccount = new HashMap<String, Object>();
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String start = " 00:00:01";
        String over = " 00:00:00";
        Calendar ctoday = (Calendar) yesterday.clone();
        ctoday.add(Calendar.DAY_OF_MONTH, 1);
        String str1 = sf.format(yesterday.getTime());
        String str2 = sf.format(ctoday.getTime());
        Date date1 = sf2.parse(str1 + start);
        Date date2 = sf2.parse(str2 + over);
        dateAccount.put("start", date1);
        dateAccount.put("end", date2);
        dateAccount.put("dataSourceId", userMT4Account.getDataSourceId());
        dateAccount.put("ibId", userRegister.getId());
        dateAccount.put("mt4Account", userMT4Account.getMt4Account());
        dateAccount.put("userMT4Account", mt4Account);
        List<CommissionBase> commissionBaseList2 = commissionBaseService.getCommissionBaseByIbId(dateAccount);
        BigDecimal amountAccount = new BigDecimal(0.0D);
        BigDecimal commissionAccount = new BigDecimal(0.0D);
        BigDecimal volumeAccount = new BigDecimal(0.0D);
        if (null != commissionBaseList2 && commissionBaseList2.size() > 0) {
            for (CommissionBase commissionBase : commissionBaseList2) {
                amountAccount = amountAccount.add(commissionBase.getPipCommission());
                volumeAccount = volumeAccount.add(commissionBase.getVolume());
                commissionAccount = commissionAccount.add(new BigDecimal(commissionBase.getPipCommission().doubleValue() * commissionBase.getRate().doubleValue()));
            }
        }
        CommissionAccountDetail commissionAccountDetail = new CommissionAccountDetail();

//		commissionAccountDetail.setBaseId(0+"");
        commissionAccountDetail.setComment("");
        commissionAccountDetail.setDataSourceId(userMT4Account.getDataSourceId());
        commissionAccountDetail.setMt4DataSourceType(userMT4Account.getMt4DatasourceType());
        commissionAccountDetail.setCurrencyCommission(commissionAccount);
        commissionAccountDetail.setCommission(amountAccount);
        commissionAccountDetail.setCreatedate(new Date());
        commissionAccountDetail.setGuid(UUID.randomUUID().toString());
        if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
            commissionAccountDetail.setIbIdType(IsInnerSalesEnum.OUTER_USER.getValue() + "");
        } else if (userRegister.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
            commissionAccountDetail.setIbIdType(IsInnerSalesEnum.INNER_SALE.getValue() + "");
        }
        commissionAccountDetail.setIbId(userRegister.getId() + "");
        commissionAccountDetail.setMt4account(userMT4Account.getMt4Account());
        commissionAccountDetail.setSettledate(yesterday.getTime());
        commissionAccountDetail.setSource("");
        commissionAccountDetail.setStatus(0 + "");
        commissionAccountDetail.setSymbol("");
        commissionAccountDetail.setUpdatedate(new Date());
        commissionAccountDetail.setUserId(userRegister.getId() + "");
        commissionAccountDetail.setVolume(volumeAccount);
        commissionAccountDetail.setBaseId(0 + "");
        commissionAccountDetailService.doInsert(commissionAccountDetail);
    }

    public static void main(String[] args) {

//		System.out.println(RateUtil.getCurrencyRate("AUD", "CAD", 6,));

    }

    @Override
    public void commissionDay() throws ParseException, PayException {
        Calendar date = Calendar.getInstance();
        Calendar today2 = (Calendar) date.clone();
        today2.add(Calendar.DAY_OF_MONTH, -1);
        calCommission(today2.getTime(), date.getTime());
        calculateMonthlyIbCommission(date);
    }

    private void commissionmethod(String mode, User user, Mt4Trades trades, UserMT4Account userMT4Account, String symbol, UserCommissionConf userCommissionConf, Set<String> groupSet, int tradingGroupId, CommissionRules commissionGroup, UserRegister userRegister2, List<DataSourceBean> dataSourceBeanList) {
        List<CommisionCoefficient> commisionCoefficientList = commisionCoefficientService.getCommisionCoefficientByRuleId(Integer.parseInt(userCommissionConf.getCommRuleId()));
        logger.info("该用户的规则的大小count:{},规则的编号rulesId:{}", commisionCoefficientList, userCommissionConf.getCommRuleId());
        if (null != commisionCoefficientList && commisionCoefficientList.size() > 0) {
            Integer roleId = user.getRoleId();
            logger.info("用户角色对应的Id:{}", roleId);
            if (null != roleId && user.getRoleId() > 0) {
                Admin admin = null;
                User user1 = user;
                insertDayDetial(admin, user1, commisionCoefficientList, trades, symbol, userMT4Account, tradingGroupId, commissionGroup.getUserdefine1(), groupSet, mode, dataSourceBeanList);
            } else {
                logger.info("该用户的上级的角色 类别websiteUserType:{}", UserAccountTypeEnum.valueOf(userRegister2.getWebsiteUserType()).getText());
                if (userRegister2.getWebsiteUserType() == UserAccountTypeEnum.IB_ACCOUNT.getValue()) {
                    User user1 = userService.getByUid(userRegister2.getId());
                    Admin admin = null;
                    insertDayDetial(admin, user1, commisionCoefficientList, trades, symbol, userMT4Account, tradingGroupId, commissionGroup.getUserdefine1(), groupSet, mode, dataSourceBeanList);
                } else if (userRegister2.getWebsiteUserType() == UserAccountTypeEnum.INTERNAL_ACCOUNT.getValue()) {
                    Admin admin = adminService.findByUserId(userRegister2.getId());
                    Admin admin1 = admin;
                    int agentAccount = 0;
                    if (null != admin) {
                        while (true) {
                            UserRegister userRegister3 = userRegisterService.findById(admin.getUserId());

                            if ((int) admin.getId() == (int) admin1.getId()) {
                                if (!(userMT4Account.getIbId() == admin.getUserId())) {
                                    userRegister3 = userRegisterService.findById(userMT4Account.getIbId());
                                }
                                agentAccount = userMT4Account.getAgentAccount();
                                logger.info("用户的直接IB的返佣账号的agentAccount:{}", agentAccount);
                            }
                            logger.info("admin userRegister3:{}", userRegister3);
                            if (null != userRegister3) {
                                logger.info("用户userRegister3的id:{}", userRegister3.getId());
                                for (CommisionCoefficient commissionCoefficient : commisionCoefficientList) {
                                    if (commissionCoefficient.getRoleId() == admin.getRoleId()) {
                                        logger.info("用户上级的roleId:{},规则设置的值coefficient:{}", admin.getRoleId(), commissionCoefficient.getCoefficient());
                                        insertIntoDetail(trades, userMT4Account, userRegister3, symbol, commissionCoefficient, tradingGroupId, commissionGroup.getUserdefine1(), groupSet, mode, 0, dataSourceBeanList);
                                    }
                                }
                                UserRegister userRegister4 = null;
                                if (agentAccount != 0) {
                                    UserMT4Account userMT4Account2 = userMT4AccountService.getUserMT4AccountByMt4Accounts(agentAccount, userMT4Account.getDataSourceId());
                                    agentAccount = userMT4Account2.getAgentMt4Account();
                                    logger.info("用户mt4Account:{}的上级返佣账号是agentAccount:{}", userMT4Account2, agentAccount);
                                    userRegister4 = userRegisterService.findById(userMT4Account2.getIbId());
                                } else {
                                    userRegister4 = userRegisterService.findById(userRegister3.getSuperior_id());
                                }
                                logger.info("admin userRegister4:{}", userRegister4);
                                if (null != userRegister4) {
                                    if (userRegister4.getSuperior_id() > 0) {
                                        admin = adminService.findByUserId(userRegister4.getId());
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}
