package com.fx.mail.service.impl;

import java.util.List;

import com.fx.mail.dao.IMailUserDao;
import com.fx.mail.model.MailUser;
import com.fx.mail.service.IMailUserService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailUserServiceImpl extends BaseVOService<MailUser> implements IMailUserService {
    @Autowired
    private IMailUserDao mailUserDao;

	@Override
	public List<MailUser> queryListByCondition(MailUser mailUserQuery) {
		return mailUserDao.findList("queryListByCondition", mailUserQuery);
	}

	@Override
	public MailUser findBySidAndisDefault(Integer sid) {
		return (MailUser) mailUserDao.findOne("findBySidAndisDefault", sid);
	}

	@Override
	public void updateAllNoDefault(Integer sid) {
		mailUserDao.doUpdate("updateAllNoDefault", sid);
	}
}