package com.fx.mail.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.mail.dao.IMailSendLogDao;
import com.fx.mail.model.MailSendLog;

@Repository
public class MailSendLogDaoImpl extends BaseDao<MailSendLog> implements IMailSendLogDao {

    public MailSendLogDaoImpl() {
        super(IMailSendLogDao.class.getName());
    }
}