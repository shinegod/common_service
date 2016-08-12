package com.fx.payment.dao.impl;

import com.fx.payment.dao.IUserMt4AccountRejectLogDao;
import com.fx.payment.model.UserMt4AccountRejectLog;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserMt4AccountRejectLogDaoImpl extends BaseDao<UserMt4AccountRejectLog> implements IUserMt4AccountRejectLogDao {

    public UserMt4AccountRejectLogDaoImpl() {
        super(IUserMt4AccountRejectLogDao.class.getName());
    }
}