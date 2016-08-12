package com.fx.user.service.impl;

import com.fx.user.dao.IUserRegisterRejectLogDao;
import com.fx.user.model.UserRegisterRejectLog;
import com.fx.user.service.IUserRegisterRejectLogService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegisterRejectLogServiceImpl extends BaseVOService<UserRegisterRejectLog> implements IUserRegisterRejectLogService {
    @Autowired
    private IUserRegisterRejectLogDao userRegisterRejectLogDao;

    @Override
    public List<UserRegisterRejectLog> getByUid(Integer uid) {
        return userRegisterRejectLogDao.findList("getByUid",uid);
    }
}