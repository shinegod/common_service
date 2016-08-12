package com.fx.mail.service;

import com.fx.common.mail.MailConfig.MailSenderType;
import com.fx.mail.model.MailServer;

import mybatis.framework.core.service.IValueObjectService;

public interface IMailServerService extends IValueObjectService<MailServer> {

	public MailServer findByServerType(int serverType);
}