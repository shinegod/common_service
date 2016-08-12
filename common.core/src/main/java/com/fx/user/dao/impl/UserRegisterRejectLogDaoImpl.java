package com.fx.user.dao.impl;

import com.fx.user.dao.IUserRegisterRejectLogDao;
import com.fx.user.model.UserRegisterRejectLog;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserRegisterRejectLogDaoImpl extends BaseDao<UserRegisterRejectLog> implements IUserRegisterRejectLogDao {

    public UserRegisterRejectLogDaoImpl() {
        super(IUserRegisterRejectLogDao.class.getName());
    }
}