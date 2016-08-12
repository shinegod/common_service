package com.fx.util.mail;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.fx.common.mail.*;
import com.fx.common.mail.exmail.ExMailConfig;
import com.fx.common.service.IDictionaryService;
import com.fx.mail.model.MailSendLog;
import com.fx.mail.model.MailServer;
import com.fx.mail.model.MailUser;
import com.fx.mail.service.IMailSendLogService;
import com.fx.mail.service.IMailServerService;
import com.fx.mail.service.IMailUserService;
import com.fx.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.common.mail.MailConfig.MailSenderType;
import org.springframework.web.context.WebApplicationContext;

public class MailUtil {

	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private String subject = "KLIMEX Important Inform";

    private static String company_host="smtp.office365.com";
    private static String company_email_username="info@klimexcm.com";
    private static String company_email_pwd="Panu8642";
    private static int company_email_port = 587;
    private static String company_name="KLIMEX";

	private String toEmail = "";
	private String mailBody = "";
	
	public void sendMail(MailConfig mailConfig,MailMessage message) {
		if(mailConfig.getMailSenderType() == MailSenderType.SEND_CLOUD){
			MailSender mailSender = mailConfig.getMailSender();
			mailSender.sendMail(message);
        }
        if(mailConfig.getMailSenderType() == MailSenderType.EX_MAIL) {
        }
	}
	
	
	
	
	

	public MailUtil(){}

	public MailUtil(String toEmail,String mailBody) {
		this.toEmail = toEmail;
		this.mailBody = mailBody;
	}
	public MailUtil(String subject,String toEmail,String mailBody){
		this.subject = subject;
		this.toEmail = toEmail;
		this.mailBody = mailBody;
	}

	public void Send() throws MessagingException, UnsupportedEncodingException
	{
		String host=company_host;
		String mail_head_value = "";
		String mail_to = toEmail;
		String mail_from = company_email_username;
		String personalName = company_name;
		String mail_body = mailBody;

		String mail_head_name = "";

		Properties props = new Properties();
		Authenticator auth = new Email_Autherticator();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "" + company_email_port);
		props.setProperty("mail.transport.protocol", "smtps");
		props.put("mail.smtp.auth", "" + true);
//		props.put("mail.debug", "true");
		props.put("mail.smtp.starttls.enable", true);
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(session);
		message.setSubject(subject);
		message.setText(mail_body, "utf-8", "html");
		message.setHeader(mail_head_name, mail_head_value);
		message.setSentDate(new Date());
		Address address = new InternetAddress(mail_from, personalName);
		message.setFrom(address);
		Address toAddress = new InternetAddress(mail_to);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		try{
			Transport.send(message);
			System.out.println("send ok!");
		}
		catch (javax.mail.SendFailedException e){
			logger.info("{}", e);
			System.out.println("Invalid Address:"+mail_to);
		}
	}

	public class Email_Autherticator extends Authenticator
	{
		public Email_Autherticator()
		{
			super();
		}

		public Email_Autherticator(String user, String pwd)
		{
			super();
			company_email_username = user;
			company_email_pwd = pwd;
		}

		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(company_email_username, company_email_pwd);
		}
	}


	public static void main(String[] args) {
		try {
			new MailUtil("rock.zhang@tianyitechs.com","123asd*jdkl3><a href='http://wwww.dowellforex.com'>dowell</a>").Send();
//			new MailUtil("986387186@qq.com","123asd*jdkl3><a href='http://wwww.dowellforex.com'>dowell</a>").Send();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//发送邮件
	public static MailResponse sendMail(MailMessage message) {
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
		//记录邮件日志
		MailSendLog mailSendLog = new MailSendLog();
		mailSendLog.setCreateIp(StringUtils.getRemoteAddr(UserUtil.getHttpServletReqeust()));
		mailSendLog.setCreateTime(DateUtil.getCurrentTime());
		if(UserUtil.getCurrAdmin() != null) {
			mailSendLog.setCreateUser(UserUtil.getCurrAdmin().getLoginName());
			mailSendLog.setUpdateUser(UserUtil.getCurrAdmin().getLoginName());
		}
		mailSendLog.setUpdateIp(StringUtils.getRemoteAddr(UserUtil.getHttpServletReqeust()));
		mailSendLog.setUpdateTime(DateUtil.getCurrentTime());
		mailSendLog.setIsResend(0);
		if(MailSenderType.EX_MAIL.getValue() == mailServer.getMailServerType()) {
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
		if(MailSenderType.SEND_CLOUD.getValue() ==  mailServer.getMailServerType()) {
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
	//发送邮件
	public static MailResponse reSendMail(MailMessage message,MailSendLog mailSendLogOld) {
		IMailSendLogService mailSendLogService = SpringUtils.getBean(IMailSendLogService.class);
		MailResponse mailResponse = new MailResponse();
		//记录邮件日志
		MailSendLog mailSendLog = new MailSendLog();
		mailSendLog.setCreateIp(StringUtils.getRemoteAddr(UserUtil.getHttpServletReqeust()));
		mailSendLog.setCreateTime(DateUtil.getCurrentTime());
		if(UserUtil.getCurrAdmin() != null) {
			mailSendLog.setCreateUser(UserUtil.getCurrAdmin().getLoginName());
			mailSendLog.setUpdateUser(UserUtil.getCurrAdmin().getLoginName());
		}
		mailSendLog.setUpdateIp(StringUtils.getRemoteAddr(UserUtil.getHttpServletReqeust()));
		mailSendLog.setUpdateTime(DateUtil.getCurrentTime());
		mailSendLog.setIsResend(mailSendLogOld.getIsResend()+1);
		//设置重发邮件前的记录id
		mailSendLog.setOldId(mailSendLogOld.getId());
		if(MailSenderType.EX_MAIL.getValue() == mailSendLogOld.getMailServerType()) {
			ExMailConfig mailConfig = new ExMailConfig();
			mailConfig.setCompany_host(mailSendLogOld.getHost());
			mailConfig.setMailSenderType(MailConfig.MailSenderType.EX_MAIL);
			mailConfig.setCompany_email_username(mailSendLogOld.getApiUser());
			mailConfig.setCompany_email_pwd(mailSendLogOld.getApiKey());
			mailConfig.setCompany_email_port(mailSendLogOld.getPort());
			mailConfig.setCompany_name(Config.getConfig("mail.template.company"));
			mailResponse = mailConfig.getMailSender().sendMail(message);
			mailSendLogService.addMailLog(mailConfig,  message, mailResponse,mailSendLog);

		}
		if(MailSenderType.SEND_CLOUD.getValue() ==  mailSendLogOld.getMailServerType()) {
			MailConfig mailConfig = new MailConfig();
			mailConfig.setApiKey(mailSendLogOld.getApiKey());
			mailConfig.setApiUser(mailSendLogOld.getApiUser());
			mailConfig.setMailSenderType(MailConfig.MailSenderType.SEND_CLOUD);
			mailConfig.setResponseEmailId("" + true);
			mailConfig.setTemplateSendUrl(mailSendLogOld.getTemplateUrl());
			mailConfig.setTextSendUrl(mailSendLogOld.getTextUrl());
			//MailSenderUtil sender = new MailSenderUtil(mailConfig);
			message.setTemplate(true);
			mailResponse =  mailConfig.getMailSender().sendMail(message);
			//记录邮件日志
			mailSendLogService.addMailLog(mailConfig,  message, mailResponse,mailSendLog);
		}
		return mailResponse;
	}


	//发送trader邮件
	public static MailResponse sendMailTrader(MailMessage message, MailSendLog mailSendLog) {
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

		if(MailSenderType.EX_MAIL.getValue() == mailServer.getMailServerType()) {
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
		if(MailSenderType.SEND_CLOUD.getValue() ==  mailServer.getMailServerType()) {
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

	public static void sendMail(MailBaseMessage mailBaseMessage) {
		  new Thread(new MailUtilThread(mailBaseMessage)).start();
	}
}

