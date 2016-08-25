package com.fx.ip.dao.impl;

import com.fx.ip.dao.IAuthorizationDao;
import com.fx.ip.model.Authorization;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 8/23/2016.
 */
@Repository
public class AuthorizationDaoImpl extends BaseDao<Authorization> implements IAuthorizationDao {

    public AuthorizationDaoImpl() {
        super(IAuthorizationDao.class.getName());
    }

}
