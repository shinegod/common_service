package com.fx.mail.service.impl;

import java.util.List;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.common.mail.MailConfig;
import com.fx.common.mail.MailConfig.MailSenderType;
import com.fx.common.mail.MailMessage;
import com.fx.common.mail.MailResponse;
import com.fx.common.mail.MailSenderUtil;
import com.fx.common.mail.exmail.ExMailConfig;
import com.fx.enums.IsDelEnum;
import com.fx.mail.Enum.MailSendStatusEnum;
import com.fx.mail.dao.IMailSendLogDao;
import com.fx.mail.model.MailSendLog;
import com.fx.mail.service.IMailSendLogService;

@Service
public class MailSendLogServiceImpl extends BaseVOService<MailSendLog> implements IMailSendLogService {
    @Autowired
    private IMailSendLogDao mailSendLogDao;

	@Override
	public void addMailLog(MailConfig mailConfig, MailMessage message,
			MailResponse mailResponse, MailSendLog mailSendLog) {
			if(mailConfig.getMailSenderType() == MailSenderType.EX_MAIL) {
				
				ExMailConfig crmMailConfig = (ExMailConfig) mailConfig;
				/**
				 * mailConfig相关
				 * 	CrmMailConfig mailConfig = new CrmMailConfig();
			        mailConfig.setCompany_host(mailServer.getHost());
			        mailConfig.setMailSenderType(MailConfig.MailSenderType.CRM_MAIL);
			        mailConfig.setCompany_email_username(mailUser.getApiUser());
			        mailConfig.setCompany_email_pwd(mailServer.getApiKey());
			        mailConfig.setCompany_email_port(mailServer.getPort());
			        mailConfig.setCompany_name(ConfigProperties.getProperty("mail.template.company"));
			        mailResponse = mailConfig.getMailSender().sendMail(message);
				 */
				mailSendLog.setHost(crmMailConfig.getCompany_host());
				mailSendLog.setMailServerType(MailSenderType.EX_MAIL.getValue());
				mailSendLog.setApiUser(crmMailConfig.getCompany_email_username());
				mailSendLog.setApiKey(crmMailConfig.getCompany_email_pwd());
				mailSendLog.setPort(crmMailConfig.getCompany_email_port());
				mailSendLog.setFromName(crmMailConfig.getCompany_name());
				mailSendLog.setMailServerName(MailConfig.MailSenderType.EX_MAIL.toString());
				mailSendLog.setMailServerType(MailSenderType.EX_MAIL.getValue());
			}
			if(mailConfig.getMailSenderType() == MailSenderType.SEND_CLOUD) {
				/**
				 * MailConfig mailConfig = new MailConfig();
			        mailConfig.setApiKey(mailServer.getApiKey());
			        mailConfig.setApiUser(mailUser.getApiUser());
			        mailConfig.setMailSenderType(MailConfig.MailSenderType.SEND_CLOUD);
			        mailConfig.setResponseEmailId("" + true);
			        mailConfig.setTemplateSendUrl(mailServer.getTemplateUrl());
			        mailConfig.setTextSendUrl(mailServer.getTextUrl());
			        MailSenderUtil sender = new MailSenderUtil(mailConfig);
			        message.setTemplate(true);
				 */
				mailSendLog.setApiKey(mailConfig.getApiKey());
				mailSendLog.setApiUser(mailConfig.getApiUser());
				mailSendLog.setMailServerName(MailConfig.MailSenderType.SEND_CLOUD.toString());
				mailSendLog.setMailServerType(MailSenderType.SEND_CLOUD.getValue());
				mailSendLog.setTemplateUrl(mailConfig.getTemplateSendUrl());
				mailSendLog.setTextUrl(mailConfig.getTextSendUrl());
			}
				/**
				 * mailMessage相关
				  	MailMessage message = new MailMessage();
					Map<String,Object> data = new HashMap<String, Object>();
			        data.put("to", userName);
					data.put("name", firstName+" "+surName);
					data.put("CompanyName", ConfigProperties.getProperty("mail.template.company"));
					data.put("login", mt4User.getLogin()+"");
					data.put("password", mt4User.getPassword());
					data.put("password_investor", mt4User.getPassword_investor());
					data.put("client_download_url", "http://www.vantagefx.cn/vantageau4setup.exe");
					data.put("register_live_url", "https://myaccount.vantagefx.cn/tradingaccounts/registerlive");
					message.setVars(data);
					message.setSubject("Klimex MT4 模拟账号注册成功");
					message.setFrom("support@vantagefx.cn");
			        message.setFromName("Klimex");
			        message.setHtml(emailContent);
			        message.setTemplate(false);
			        message.setTemplateName("demo_mt4_notice");
			        message.setTo(userName);
				 */
				mailSendLog.setVars(gson.toJson(message.getVars()));
				mailSendLog.setSubject(message.getSubject());
				mailSendLog.setFromMail(message.getFrom());
				mailSendLog.setFromName(message.getFromName());
				mailSendLog.setHtml(message.getHtml());
				mailSendLog.setTemplateInvokeName(message.getTemplateName());
				//mailSendLog.setIsTemplate((message.isTemplate()?1:0));
				mailSendLog.setToMail(message.getTo());
				if(mailResponse.isSuccess()) {
					mailSendLog.setStatus(MailSendStatusEnum.SUCCESS.getValue());
				} else {
					mailSendLog.setStatus(MailSendStatusEnum.FAIL.getValue());
				}
				if(message.isUseMaillist()) {
					mailSendLog.setUseMaillist(message.getTo());
				}
				mailSendLog.setBcc(message.getBcc());
				mailSendLog.setCc(message.getCc());
				mailSendLog.setIsDel(IsDelEnum.NO.getValue());
				mailSendLog.setIsResend(mailSendLog.getIsResend());//是否属于重发，存入重发次数
				mailSendLog.setReplyTo(message.getReplyto());
				mailSendLog.setResponse(mailResponse.getMsg());
				mailSendLogDao.doInsert("insert", mailSendLog);
			
	}

	@Override
	public List<MailSendLog> queryListByCondition(MailSendLog mailSendLogQuery) {
		return mailSendLogDao.findList("queryListByCondition", mailSendLogQuery);
	}
}