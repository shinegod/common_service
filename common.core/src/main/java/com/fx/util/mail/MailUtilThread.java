package com.fx.util.mail;

import com.fx.common.mail.MailBaseMessage;
import com.fx.common.mail.MailConfig;
import com.fx.common.mail.MailMessage;
import com.fx.common.mail.MailResponse;
import com.fx.common.mail.exmail.ExMailConfig;
import com.fx.common.service.IDictionaryService;
import com.fx.mail.model.MailSendLog;
import com.fx.mail.model.MailServer;
import com.fx.mail.model.MailUser;
import com.fx.mail.service.IMailSendLogService;
import com.fx.mail.service.IMailServerService;
import com.fx.mail.service.IMailUserService;
import com.fx.util.Config;
import com.fx.util.DateUtil;
import com.fx.util.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Barry on 2016/1/5.
 */
public class MailUtilThread implements Runnable {

    private final MailBaseMessage mailBaseMessage;

    public MailUtilThread(MailBaseMessage mailBaseMessage) {
        this.mailBaseMessage = mailBaseMessage;
    }

    @Override
    public void run() {
        MailMessage message = new MailMessage();
        Map<String,Object> map = new HashMap<>();
        message.setSubject(mailBaseMessage.getSubject());
        message.setFrom(Config.getConfig("mail.from"));
        message.setFromName(Config.getConfig("mail.fromName"));
        message.setHtml(MailGetHtmlUtil.getEmailContent(mailBaseMessage.getTemplateName(),mailBaseMessage.getMap()));
        message.setTemplate(false);
        message.setTemplateName(mailBaseMessage.getTemplateName());
        if(mailBaseMessage.getTo().contains(",")) {
            String[] emailArr = mailBaseMessage.getTo().split(",");
            for (String to : emailArr) {
                message.setTo(to);
                map = mailBaseMessage.getMap();
                map.put("to",to);
                message.setVars(map);
                send(message,getMailLog(mailBaseMessage));
            }
        } else {
            map = mailBaseMessage.getMap();
            map.put("to",mailBaseMessage.getTo());
            message.setVars(map);
            message.setTo(mailBaseMessage.getTo());
            send(message,getMailLog(mailBaseMessage));
        }
    }

    private static MailSendLog getMailLog(MailBaseMessage mailBaseMessage) {
        //记录邮件日志
        MailSendLog mailSendLog = new MailSendLog();
        if(mailBaseMessage != null) {
            mailSendLog.setCreateUser(mailBaseMessage.getUserName());
            mailSendLog.setUpdateUser(mailBaseMessage.getUserName());
            mailSendLog.setCreateIp(mailBaseMessage.getIp());
            mailSendLog.setCreateTime(DateUtil.getCurrentTime());
            mailSendLog.setUpdateIp(mailBaseMessage.getIp());
            mailSendLog.setUpdateTime(DateUtil.getCurrentTime());
        }

        mailSendLog.setIsResend(0);
        return mailSendLog;
    }
    private static MailResponse send(MailMessage message, MailSendLog mailSendLog) {
        IMailServerService mailServerService = SpringUtils.getBean(IMailServerService.class);
        IDictionaryService dictionaryService = SpringUtils.getBean(IDictionaryService.class);
        IMailUserService mailUserService = SpringUtils.getBean(IMailUserService.class);
        IMailSendLogService mailSendLogService = SpringUtils.getBean(IMailSendLogService.class);

        MailResponse mailResponse = new MailResponse();
        MailServer mailServer = mailServerService.findByServerType(Integer.parseInt(dictionaryService.findByCode("defaultMail").getDescription()));
        if(mailServer == null) {
            List<MailServer> list = mailServerService.findAll();
            if(list != null && list.size() > 0 ) {
                mailServer = list.get(0);
            }
        }
        MailUser mailUser = mailUserService.findBySidAndisDefault(mailServer.getId());
        if(mailUser == null ){
            List<MailUser> allUsers = mailUserService.findAll();
            if(allUsers != null && allUsers.size() > 0 ) {
                mailUser = allUsers.get(0);
            }
        }
        if(MailConfig.MailSenderType.EX_MAIL.getValue() == mailServer.getMailServerType()) {
            ExMailConfig mailConfig = new ExMailConfig();
            mailConfig.setCompany_host(mailServer.getHost());
            mailConfig.setMailSenderType(MailConfig.MailSenderType.EX_MAIL);
            mailConfig.setCompany_email_username(mailUser.getApiUser());
            mailConfig.setCompany_email_pwd(mailServer.getApiKey());
            mailConfig.setCompany_email_port(mailServer.getPort());
            mailConfig.setCompany_name(Config.getConfig("mail.template.company"));
            mailResponse = mailConfig.getMailSender().sendMail(message);
            mailSendLogService.addMailLog(mailConfig,  message, mailResponse,mailSendLog);

        }
        if(MailConfig.MailSenderType.SEND_CLOUD.getValue() ==  mailServer.getMailServerType()) {
            MailConfig mailConfig = new MailConfig();
            mailConfig.setApiKey(mailServer.getApiKey());
            mailConfig.setApiUser(mailUser.getApiUser());
            mailConfig.setMailSenderType(MailConfig.MailSenderType.SEND_CLOUD);
            mailConfig.setResponseEmailId("" + true);
            mailConfig.setTemplateSendUrl(mailServer.getTemplateUrl());
            mailConfig.setTextSendUrl(mailServer.getTextUrl());
            //MailSenderUtil sender = new MailSenderUtil(mailConfig);
            message.setTemplate(true);
            mailResponse =  mailConfig.getMailSender().sendMail(message);
            //记录邮件日志
            mailSendLogService.addMailLog(mailConfig,  message, mailResponse,mailSendLog);
        }
        return mailResponse;
    }
}
