package com.fx.mail.service.impl;

import com.fx.common.mail.MailConfig.MailSenderType;
import com.fx.mail.dao.IMailServerDao;
import com.fx.mail.model.MailServer;
import com.fx.mail.service.IMailServerService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServerServiceImpl extends BaseVOService<MailServer> implements IMailServerService {
    @Autowired
    private IMailServerDao mailServerDao;

	@Override
	public MailServer findByServerType(int serverType) {
		return (MailServer) mailServerDao.findOne("findByServerType", serverType);
	}

}