package com.fx.crm.transfer.impl;

import com.fx.MT4.enums.MT4OperateTypeEnum;
import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4AccountUtil;
import com.fx.MT4.vo.MT4User;
import com.fx.common.mail.MailBaseMessage;
import com.fx.common.model.Dictionary;
import com.fx.common.service.IDictionaryService;
import com.fx.crm.transfer.ITransferService;
import com.fx.crm.transfer.enums.Mt4TranferSwitchEnum;
import com.fx.crm.transfer.util.ConfigProperties;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.enums.CurrencyStatusEnum;
import com.fx.enums.MT4DetailStatusEnum;
import com.fx.enums.MT4DetailTypeEnum;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.payment.model.MT4Detail;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IMT4DetailService;
import com.fx.payment.service.IUserAccountService;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.user.enums.MT4TransferStatusEnum;
import com.fx.user.model.MT4Transfer;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IMT4TransferService;
import com.fx.user.service.IUserRegisterService;
import com.fx.util.DateUtil;
import com.fx.util.mail.MailUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinker on 16/2/19.
 */
@Service
public class TransferServiceImpl implements ITransferService {

    public static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Autowired
    private IMT4TransferService transferService;

    @Autowired
    private IUserMT4AccountService userMT4AccountService;

    @Autowired
    private IDataSourceBeanService dataSourceBeanService;

    @Autowired
    private IUserAccountService userAccountService;

    @Autowired
    private IUserRegisterService userRegisterService;

    @Autowired
    private IMT4DetailService mT4DetailService;

    @Autowired
    private IMt4UsersService mt4UsersService;

    @Autowired
    private IDictionaryService dictionaryService;


    private final static String COMMENT_INFO = "【自动转账】";
    private static int flag = 0;

    @Override
    public void doTransferTask() {//15分
        if (flag == 0) {
            flag = 1;
            try {
                //TODO 系统级别判断 自动 手动
                Dictionary dic = dictionaryService.findByCode("default_mt4_transfer_switch");

                if (Integer.parseInt(dic.getDescription()) != Mt4TranferSwitchEnum.MT_4_TRANFER_SWITCH_ON.getValue()) {
                    logger.info("当前为手动转账...状态:{}", dic.getDescription());
                    return;
                }
                HashMap<String, Object> params = Maps.newHashMap();
                params.put("status", "" + MT4TransferStatusEnum.NO_OPERATOR_AUTO.getValue());
                List<MT4Transfer> taskList = transferService.queryListByStatus(params);
                logger.info("当前时间{}有待处理转账记录{}条...", DateUtil.getCurrentTime(), taskList == null ? 0 : taskList.size());
                for (MT4Transfer mt4Transfer : taskList) {
                    transfer(mt4Transfer);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                flag = 0;
            }
        } else {
            return;
        }
    }

    /**
     * 进行mt4 转账
     */
    private void transfer(MT4Transfer mt4Transfer) {
        if (mt4Transfer == null) {
            return;
        }
        //TODO 重新查询数据库
        MT4Transfer tempMt4Trader = null;
        tempMt4Trader = transferService.findById(mt4Transfer.getId());
        if (tempMt4Trader == null) {
            return;
        }
        mt4Transfer = null;// transferService.findById(mt4Transfer.getId());
        tempMt4Trader.setAudit_amount(tempMt4Trader.getAmount());
        mt4Transfer = tempMt4Trader;
        if (mt4Transfer.getStatus() != MT4TransferStatusEnum.NO_OPERATOR_AUTO.getValue()) {
            logger.info("用户{} 的转账记录{} , 转出账号 {} 转入账号 {} 状态为 {} 定时任务不需处理", mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account(), mt4Transfer.getStatus());
            return;
        }

        UserRegister byId = userRegisterService.findById(mt4Transfer.getUid());
        //判断当前记录是待转状态 判断当前转出账号余额
        UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Account(mt4Transfer.getFrommt4account());
        DataSourceBean dataSourceBean = dataSourceBeanService.findById(userMT4Account.getDataSourceId());

        //TODO 净值 保证金
        MT4User mt4user = null;
        Mt4Users mt4Users = null;
        try {
            //mt4user=MT4AccountUtil.getMT4ClientInfo(mt4Transfer.getFrommt4account(), QueryMtT4GroupIdEnum.LIVE,dataSourceBean);
            mt4Users = mt4UsersService.getMt4UsersByMt4Account(mt4Transfer.getFrommt4account(), dataSourceBean.getId());
        } catch (Exception e) {
            logger.error("用户{} 的转账记录{} , 账号 {} 转入账号 {} ", mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

            String comment = " 获取净值 保证金 信用额度 dll 出错";
            Integer status = MT4TransferStatusEnum.AMOUNT_NOT_TRANSFERRED.getValue();
            this.updateTranferStatus(mt4Transfer, comment, status);

            Integer mt4DetailStatus = MT4DetailStatusEnum.FAIL.getValue();
            Integer mt4DetailType = MT4DetailTypeEnum.WITHDRAW.getValue();
            this.updateMt4Detail(mt4user, mt4Transfer, mt4DetailStatus, mt4DetailType);

            //TODO 失败的邮件  修改 收件人
            this.sendFailEmailMessage(byId, mt4Transfer, comment);
            return;
        }
        if (judgeBalance(mt4Transfer, dataSourceBean, mt4Users)) {
            try {
                //TODO 转出
                userAccountService.mulMT4TransferMoney(mt4Transfer, MT4OperateTypeEnum.TRANSFER_OUT.getText() + " to " + mt4Transfer.getTomt4account(), dataSourceBean);
            } catch (Exception e) {
                logger.error("用户{} 的转账记录{} , 账号 {} 转入账号 {} ", mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());


                String comment = " 出金失败，dll报错，内部人员处理";
                Integer status = MT4TransferStatusEnum.AMOUNT_NOT_TRANSFERRED.getValue();
                this.updateTranferStatus(mt4Transfer, comment, status);

                Integer mt4DetailStatus = MT4DetailStatusEnum.FAIL.getValue();
                Integer mt4DetailType = MT4DetailTypeEnum.WITHDRAW.getValue();
                this.updateMt4Detail(mt4user, mt4Transfer, mt4DetailStatus, mt4DetailType);

                //TODO 失败的邮件  修改 收件人
                this.sendFailEmailMessage(byId, mt4Transfer, comment);

                return;
            }
            try {
                //TODO 转入
                userAccountService.addMT4TransferMoney(MT4OperateTypeEnum.TRANSFER_IN.getText() + " from " + mt4Transfer.getFrommt4account(), mt4Transfer, dataSourceBean);
            } catch (Exception e) {
                //TODO 金额转入错误
                logger.error("用户{} 的转账记录{} , 账号 {} 转入账号 {} ", mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

                String comment = "入金失败，dll报错，内部人员处理";
                Integer status = MT4TransferStatusEnum.AMOUNT_NOT_ROLL.getValue();
                this.updateTranferStatus(mt4Transfer, comment, status);

                Integer mt4DetailStatus = MT4DetailStatusEnum.FAIL.getValue();
                Integer mt4DetailType = MT4DetailTypeEnum.DEPOSIT.getValue();
                this.updateMt4Detail(mt4user, mt4Transfer, mt4DetailStatus, mt4DetailType);

                //TODO 失败的邮件  修改 收件人
                this.sendFailEmailMessage(byId, mt4Transfer, comment);

                return;

            }
            try {
                mt4Transfer.setStatus(MT4TransferStatusEnum.SUCCESS_AUTO.getValue());
                mt4Transfer.setComment(COMMENT_INFO + " 审核通过转账成功");
                mt4Transfer.setUpdateTime(DateUtil.getCurrentTime());
                transferService.doUpdateById(mt4Transfer);
                logger.info("用户{}  账号 {} 转入账号 {} 转账成功", mt4Transfer.getUid(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

                //TODO 成功的邮件
                this.sendSucMailMessage(byId, mt4Transfer);


                //SUCCESS_AUTO_EQUITY_NEGATIVE
            } catch (Exception e) {
                //TODO 数据库转账成功但是更新失败
                logger.error("用户{}  账号 {} 转入账号 {} 转账成功,但是数据库更新状态失败", mt4Transfer.getUid(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

                String comment = "转账成功,但是更新数据库状态失败";
                Integer status = MT4TransferStatusEnum.SUCCESS_AUTO_FAILD.getValue();
                this.updateTranferStatus(mt4Transfer, comment, status);

                Integer mt4DetailStatus = MT4DetailStatusEnum.FAIL.getValue();
                Integer mt4DetailType = MT4DetailTypeEnum.TRANSFER.getValue();
                this.updateMt4Detail(mt4user, mt4Transfer, mt4DetailStatus, mt4DetailType);

                //TODO 失败的邮件  修改 收件人
                this.sendFailEmailMessage(byId, mt4Transfer, comment);

                return;

            }
            //SUCCESS_AUTO_EQUITY_NEGATIVE
            try {
                MT4User mt4user1 = MT4AccountUtil.getMT4ClientInfo(mt4Transfer.getFrommt4account(), QueryMtT4GroupIdEnum.LIVE, dataSourceBean);
                if (mt4user1.getBalance() < 0) {
                    mt4Transfer.setStatus(MT4TransferStatusEnum.SUCCESS_AUTO_EQUITY_NEGATIVE.getValue());
                    mt4Transfer.setComment(COMMENT_INFO + " 转出成功但是净值为负数");
                    mt4Transfer.setUpdateTime(DateUtil.getCurrentTime());
                    transferService.doUpdateById(mt4Transfer);
                    logger.info("用户{}  账号 {} 转入账号 {} 转出成功但是净值为负数", mt4Transfer.getUid(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());
                }
            } catch (Exception e) {

                logger.error("用户{} 的转账记录{} , 账号 {} 转入账号 {}  金额转出成功数据库状态成功但是为净值", mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

                String comment = "金额转出成功数据库状态成功但是为净值";
                Integer status = MT4TransferStatusEnum.SUCCESS_AUTO_EQUITY_NEGATIVE.getValue();
                this.updateTranferStatus(mt4Transfer, comment, status);

                Integer mt4DetailStatus = MT4DetailStatusEnum.SUCCESS.getValue();
                Integer mt4DetailType = MT4DetailTypeEnum.TRANSFER.getValue();
                this.updateMt4Detail(mt4user, mt4Transfer, mt4DetailStatus, mt4DetailType);

                //TODO 失败的邮件  修改 收件人
                this.sendFailEmailMessage(byId, mt4Transfer, comment);

                return;
            }

        } else {
            //TODO 失败处理完成
            logger.warn("用户{}  账号 {} 转入账号 {} 余额不足", mt4Transfer.getUid(), mt4Transfer.getFrommt4account(), mt4Transfer.getTomt4account());

            String comment = "余额不足";
            Integer status = MT4TransferStatusEnum.NO_INSUFFICIENT_BALANCE.getValue();
            this.updateTranferStatus(mt4Transfer, comment, status);

            //TODO 余额不足邮件
            this.sendFailEmailMessage(byId, mt4Transfer, comment);

        }
    }

    private void sendSucMailMessage(UserRegister byId, MT4Transfer mt4Transfer) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("to", byId.getEmail());
        data.put("name", byId.getCnName());
        data.put("login", mt4Transfer.getFrommt4account() + "");
        data.put("to_login", mt4Transfer.getTomt4account() + "");
        data.put("amount", mt4Transfer.getAmount());//转入金额
        data.put("CompanyName", getPropertyCompanyInfo());
        MailUtil.sendMail(new MailBaseMessage(null, null, byId.getEmail(), data, "MT4 transfer succeed", "mt4_transfer_success"));
    }


    private void sendFailEmailMessage(UserRegister byId, MT4Transfer mt4Transfer, String comment) {
        String toEmailNAme = getTranferEmaiAddress();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "root");
        data.put("query_type", "auto transfer");
        data.put("id", byId.getId());
        data.put("username", byId.getCnName());
        data.put("from_login", mt4Transfer.getFrommt4account());
        data.put("to_login", mt4Transfer.getTomt4account());
        data.put("amount", mt4Transfer.getAmount());
        data.put("CompanyName", getPropertyCompanyInfo());
        data.put("comment", comment);
        MailUtil.sendMail(new MailBaseMessage(null, "System send", toEmailNAme, data, "Task processing", "mt4_tranfer_fail"));

    }


    private void updateMt4Detail(MT4User mt4user, MT4Transfer mt4Transfer, Integer mt4DetailStatus, Integer mt4DetailType) {
        MT4Detail mt4Detail = insetMT4Detail(mt4Transfer.getUid(), mt4Transfer.getId(), mt4Transfer.getAmount());
        mt4Detail.setBalance(new BigDecimal(mt4user.getBalance()));
        mt4Detail.setStatus(mt4DetailStatus);
        mt4Detail.setType(mt4DetailType);

        mt4Detail.setMt4Account(mt4Transfer.getFrommt4account());
        mT4DetailService.doInsert(mt4Detail);

    }

    private void updateTranferStatus(MT4Transfer mt4Transfer, String comment, Integer status) {
        mt4Transfer.setStatus(status);
        mt4Transfer.setComment(COMMENT_INFO + " " + comment);
        mt4Transfer.setUpdateTime(DateUtil.getCurrentTime());
        transferService.doUpdateById(mt4Transfer);
    }


    //TODO 常量
    private String getPropertyCompanyInfo() {
        return ConfigProperties.getProperty("mail.template.company");
    }

    private String getTranferEmaiAddress() {
        return ConfigProperties.getProperty("tranfer_audit_to_email_address");
    }

    /**
     * 判断可用余额是否大于当前转账金额
     */
    private boolean judgeBalance(MT4Transfer mt4Transfer, DataSourceBean dataSourceBean, Mt4Users fromDllUser) {

        //TODO 转出金额
        BigDecimal tranferOut = mt4Transfer.getAmount();
        logger.warn("currentUserMT4Account: {}  account {} ", mt4Transfer.getFrommt4account(), tranferOut);

        //TODO 可用余额 = 净值-信誉-保证金
        Double available = fromDllUser.getEquity() - fromDllUser.getMargin() - fromDllUser.getCredit();
        logger.info("用户{} 的转账记录{} , 实际可操作余额{} ", mt4Transfer.getUid(), mt4Transfer.getId(), available);
        //equity－credit
        return available >= tranferOut.doubleValue() ? true : false;
    }


    public MT4Detail insetMT4Detail(int uid, int tradeId, BigDecimal operMoney) {
        MT4Detail mt4Detail = new MT4Detail();
        mt4Detail.setCreateIp("127.0.0.1");
        mt4Detail.setCreateTime(DateUtil.getCurrentTime());
        mt4Detail.setCreateUser(String.valueOf(uid));
        mt4Detail.setCurrency(CurrencyStatusEnum.USD.getText());
        mt4Detail.setUpdateIp("127.0.0.1");
        mt4Detail.setUpdateUser(String.valueOf(uid));
        mt4Detail.setUpdateTime(DateUtil.getCurrentTime());
        mt4Detail.setOperMoney(operMoney);
        mt4Detail.setTradeid(tradeId);
        mt4Detail.setUid(uid);
        return mt4Detail;
    }


}