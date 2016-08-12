package com.fx.payment.service.impl;

import com.fx.payment.dao.IUserMt4AccountRejectLogDao;
import com.fx.payment.model.UserMt4AccountRejectLog;
import com.fx.payment.service.IUserMt4AccountRejectLogService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMt4AccountRejectLogServiceImpl extends BaseVOService<UserMt4AccountRejectLog> implements IUserMt4AccountRejectLogService {
    @Autowired
    private IUserMt4AccountRejectLogDao userMt4AccountRejectLogDao;

    @Override
    public List<UserMt4AccountRejectLog> getByMid(int mid) {
        return  userMt4AccountRejectLogDao.findList("getByMid",mid);
    }
}