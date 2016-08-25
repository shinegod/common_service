package com.fx.ip.service.impl;

import com.fx.ip.dao.IAuthorizationDao;
import com.fx.ip.model.Authorization;
import com.fx.ip.service.IAuthorizationService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Michael on 8/23/2016.
 */
@Service
public class AuthorizationServiceImpl extends BaseVOService<Authorization> implements IAuthorizationService {

    @Autowired
    IAuthorizationDao authorizationDao;

}
