package com.fx.mail.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.mail.dao.IMailServerDao;
import com.fx.mail.model.MailServer;

@Repository
public class MailServerDaoImpl extends BaseDao<MailServer> implements IMailServerDao {

    public MailServerDaoImpl() {
        super(IMailServerDao.class.getName());
    }
}