package com.fx.mail.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.mail.dao.IMailUserDao;
import com.fx.mail.model.MailUser;

@Repository
public class MailUserDaoImpl extends BaseDao<MailUser> implements IMailUserDao {

    public MailUserDaoImpl() {
        super(IMailUserDao.class.getName());
    }
}