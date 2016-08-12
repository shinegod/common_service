package com.fx.imp;

import com.fx.bonus.dao.ICommisionCoefficientDao;
import com.fx.bonus.dao.ICommissionRulesDao;
import com.fx.bonus.model.CommisionCoefficient;
import com.fx.bonus.model.CommissionRules;
import com.fx.bonus.model.TradingGroup;
import com.fx.bonus.service.ICommissionRulesService;
import com.fx.bonus.service.ITradingGroupService;
import com.fx.imp.dao.ICommissionSetImpDao;
import com.fx.imp.model.CommissionSetImp;
import com.fx.imp.service.ICommissionSetImpService;
import com.fx.payment.dao.IUserMT4AccountDao;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/8/26.
 */
@Service
public class CommissionSetImport {

    private static final Logger logger = LoggerFactory.getLogger(CommissionSetImport.class);

    public static Map<String, Integer> dataSourceMap = null;

    public static Map<Integer, String> dataSourceReversaltMap = null;

    public static final String SETTLE_UNIT = "PER_LOT";

    public static Map<String, String> settleModel = null;


    public static Map<String, String> settleModelReversalMap = null;

    public static Map<String, Integer> productGroup = null;

    public static final String COMMISSION_TYPE = "1";

    public static final String STRING_LINK_CHAR = "_";

    public static final String UPDATEOR = "import";

    public static final Integer STATUS = 0;

    public static final int[] roleIdArrays = {1,129,130,131,132,133,134,135, 136,137,138,139};


    static {
        dataSourceMap = new HashMap<>();
        dataSourceMap.put("MXT", 16);
        dataSourceMap.put("VFX-AU", 7);
        dataSourceMap.put("VFX-UK", 6);

        settleModel = new HashMap<>();
        settleModel.put("$", "FIXED");
        settleModel.put("%", "PERCENTAGE");
        settleModel.put("pip", "POINT");

        dataSourceReversaltMap = new HashMap<>();
        dataSourceReversaltMap.put(16 ,"MXT");
        dataSourceReversaltMap.put( 7, "VFX-AU");
        dataSourceReversaltMap.put( 6, "VFX-UK");

        settleModelReversalMap = new HashMap<>();
        settleModelReversalMap.put( "FIXED", "$");
        settleModelReversalMap.put("PERCENTAGE", "%");
        settleModelReversalMap.put("POINT", "pip");


    }

    /**
     * 获取产品组
     * @return
     */
    public Map<String,Integer> getProductGroup() {
        ITradingGroupService service = SpringUtils.getBean(ITradingGroupService.class);
        List<TradingGroup> productList = service.findAll();
        Map<String, Integer> productGroup = new HashMap<>();
        for (TradingGroup g : productList) {
            if(0 == g.getStatus()) {
                productGroup.put(g.getGroupName(), g.getId());
            }
        }
        return productGroup;
    }

    /**
     * 程序执行主方法
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        CommissionSetImport imp = context.getBean(CommissionSetImport.class);

        productGroup = imp.getProductGroup();

        imp.createCommGroup();
    }

    /**
     * 第一步
     * 创建规则分组
     */
    public void createCommGroup() {
        createGroup();
        //第二步:创建分组下规则
        createRule();
        //第三步:更新返回用账户对应的userid
//        updateUserId2Ud4();
        //第四步:创建ib和佣金设置的关系
        //TODO:
    }

    private void createGroup() {
        ICommissionSetImpDao dao = SpringUtils.getBean(ICommissionSetImpDao.class);
        ICommissionSetImpService impService = SpringUtils.getBean(ICommissionSetImpService.class);
        List<CommissionSetImp> commGroup = impService.getCommGroup();
//        List<CommissionSetImp> commGroup = dao.getCommGroup();
        ICommissionRulesService groupService = SpringUtils.getBean(ICommissionRulesService.class);

        for (CommissionSetImp imp : commGroup) {
            CommissionRules cgroup = covertGroup(imp);
            if (cgroup == null) {
                logger.info("--------->{}", imp.toString());
                continue;
            }
            Integer groupId = groupService.doInsert(cgroup);
            imp.setUd1(cgroup.getRuleName());
            //批量更新数据导入表
            dao.updateBatch(null, imp);
        }
    }

    /**
     * 创建分组下规则
     */
    private void createRule() {
        ICommissionSetImpDao impDao = SpringUtils.getBean(ICommissionSetImpDao.class);
        ICommissionRulesDao groupDao = SpringUtils.getBean(ICommissionRulesDao.class);
        List<CommissionRules> groupList = groupDao.findAllByStatus();
        for (CommissionRules group : groupList) {
            group.setDataSourceName(dataSourceReversaltMap.get(group.getDataSourceId()));
            group.setSettleMode(settleModelReversalMap.get(group.getSettleMode()));
//            List<CommissionSetImp> impValList = impDao.findList("selectValImpByRule", group);
//            for (CommissionSetImp val : impValList) {
//                group.setUserdefine2(val.getCommissionVal());
                CommissionSetImp impOne = (CommissionSetImp) impDao.findOne("selectImpByRuleGroup", group);
                createRuleDetail(impOne, group.getId());
//            }
        }
    }

    //更新ib的userId
    private void updateUserId2Ud4() {
        ICommissionSetImpDao dao = SpringUtils.getBean(ICommissionSetImpDao.class);
        List<CommissionSetImp> list = dao.findAll("selectAll");
        IUserMT4AccountDao accountDao = SpringUtils.getBean(IUserMT4AccountDao.class);
        for (CommissionSetImp imp : list) {
            UserMT4Account params =  new UserMT4Account();
            params.setMt4Account(imp.getLogin());
            params.setStatus(3);
            params.setDataSourceId(dataSourceMap.get(imp.getDatasource()));
            Object obj = accountDao.findOne("selectCommissionUserID", params);
            int ibUserId = 0;
            if (obj != null) {
                UserMT4Account account = (UserMT4Account) obj;
                ibUserId = account.getUid();
            }
            imp.setUd4("" + ibUserId);
            dao.doUpdate("updateUserID", imp);
        }
    }

    public void createRuleDetail(CommissionSetImp imp, int ruleGroupId) {
        ICommissionSetImpDao impDao = SpringUtils.getBean(ICommissionSetImpDao.class);
        ICommissionRulesService groupService = SpringUtils.getBean(ICommissionRulesService.class);

        //TODO: check     sql id = selectCommCoefficient
        List<CommissionSetImp> commCoefficientList = impDao.getCommCoefficient(imp);
        Map<String, Object> params = new HashMap<>();
        params.put("ruleName", imp.getUd1());
        params.put("datasource",dataSourceMap.get(imp.getDatasource()));
        params.put("settleMode", settleModel.get(imp.getClosingForm()));



        for (CommissionSetImp comCoefficient : commCoefficientList) {
            int ruleId = getRuleId(ruleGroupId);
            for (int i = 0; i < roleIdArrays.length; i++) {
                insertCommDetail(roleIdArrays[i], comCoefficient.getCommissionVal(), ruleId);
            }
            if(StringUtils.isNotBlank(comCoefficient.getCommissionVal())){
                //ud2 groupId
                comCoefficient.setUd2("" + ruleGroupId);
                //ud3 ruleId
                comCoefficient.setUd3("" + ruleId);

                impDao.updateBatch(comCoefficient);
            }
        }


    }

    /**
     * 插入详细表
     */
    private void insertCommDetail(int roleId, String commissionVal, int ruleId) {
        CommisionCoefficient cc = new CommisionCoefficient();
        cc.setUpdateUser(UPDATEOR);
        cc.setRoleId(roleId);
        cc.setRuleId(ruleId);
        if(roleId == 139){
            cc.setCoefficient(commissionVal);
        }else{
            cc.setCoefficient(""+0);
        }
        cc.setUpdateDate(new Date());
        ICommisionCoefficientDao dao = SpringUtils.getBean(ICommisionCoefficientDao.class);
        dao.doInsert("insert", cc);
    }

    private int getRuleId(int ruleGroupId) {

//        ICommissionRulesDao dao = SpringUtils.getBean(ICommissionRulesDao.class);
//        CommissionRules rule = new CommissionRules();
//        rule.setRuleGroupId(ruleGroupId);
//        rule.setStatus(0);
//        dao.doInsert("insert", rule);
//        CommissionRules params = new CommissionRules();
//        params.setRuleGroupId(ruleGroupId);
//        CommissionRules ruleObj = (CommissionRules) dao.findOne("findNewInsert", params);
        return 0;
    }

    private CommissionRules covertGroup(CommissionSetImp commGroup) {
        CommissionRules cgroup = new CommissionRules();
        String rulename = commGroup.getDatasource() + STRING_LINK_CHAR + commGroup.getProductGroup();
        cgroup.setAccountGroup(STATUS);
        cgroup.setCommissionType(COMMISSION_TYPE);
        cgroup.setUserdefine1(COMMISSION_TYPE);
        cgroup.setDataSourceId(dataSourceMap.get(commGroup.getDatasource()));
        cgroup.setRuleName(rulename);
        if (productGroup.get(rulename) == null) {
            logger.info("===============>{} can't get product setting ", rulename);
            return null;
        }
        cgroup.setTradingGroupId(productGroup.get(rulename));
        cgroup.setSettleMode(settleModel.get(commGroup.getClosingForm()));
        cgroup.setSettleUnit(SETTLE_UNIT);
        cgroup.setUpdateUser(UPDATEOR);
        cgroup.setStatus(STATUS);
        return cgroup;
    }


}
