package com.fx.mail.service;

import java.util.List;

import com.fx.common.mail.MailConfig;
import com.fx.common.mail.MailMessage;
import com.fx.common.mail.MailResponse;
import com.fx.mail.model.MailSendLog;

import mybatis.framework.core.service.IValueObjectService;

public interface IMailSendLogService extends IValueObjectService<MailSendLog> {
	
	/**
	 * 记录邮件日志
	 * @param mailConfig
	 * @param message
	 * @param mailResponse
	 * @param mailSendLog 
	 */
	public void addMailLog(MailConfig mailConfig, MailMessage message,
			MailResponse mailResponse, MailSendLog mailSendLog);

	public List<MailSendLog> queryListByCondition(MailSendLog mailSendLogQuery);
}