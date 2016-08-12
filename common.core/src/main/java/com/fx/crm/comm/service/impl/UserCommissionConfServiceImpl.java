package com.fx.crm.comm.service.impl;

import com.fx.crm.comm.dao.IUserCommissionConfDao;
import com.fx.crm.comm.model.UserCommissionConf;
import com.fx.crm.comm.service.IUserCommissionConfService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCommissionConfServiceImpl extends BaseVOService<UserCommissionConf> implements IUserCommissionConfService {
    @Autowired
    private IUserCommissionConfDao userCommissionConfDao;

    @Override
    public List<UserCommissionConf> findByUserId(int uid) {
        return userCommissionConfDao.findByUserId(uid);
    }

    @Override
    public int doDeleteByUserId(int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return userCommissionConfDao.doDelete("deleteByUserId", params);
    }
	@Override
	public List<UserCommissionConf> findByUidAccount(Map<String, Object> params) {
		return userCommissionConfDao.findList("findByUidAccount", params);
	}
    @Override
    public List<UserCommissionConf> findByUserIdAndAccount(int uid, int account) {
        return userCommissionConfDao.findByUserIdAndAccount(uid,account);
    }

	@Override
	public UserCommissionConf findUserCommissionConfByRulesId(String string) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("rulesId", string);
		return (UserCommissionConf)userCommissionConfDao.findOne("findUserCommissionConfByRulesId",params);
	}
}