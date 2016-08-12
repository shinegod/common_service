package com.fx.util;

import com.fx.common.mail.MailConfig;
import com.fx.common.mail.MailConfig.MailSenderType;
import com.fx.common.mail.MailMessage;
import com.fx.common.mail.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private String subject = "KLIMEX Important Inform";

    private static String company_host = "smtp.office365.com";
    private static String company_email_username = "info@klimexcm.com";
    private static String company_email_pwd = "Panu8642";
    private static int company_email_port = 587;
    private static String company_name = "KLIMEX";

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
			new MailUtil("3056775653@qq.com","123asd*jdkl3><a href='http://wwww.dowellforex.com'>dowell</a>").Send();
//			new MailUtil("986387186@qq.com","123asd*jdkl3><a href='http://wwww.dowellforex.com'>dowell</a>").Send();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

