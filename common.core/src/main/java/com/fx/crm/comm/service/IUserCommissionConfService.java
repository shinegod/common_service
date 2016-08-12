package com.fx.crm.comm.service;

import com.fx.crm.comm.model.UserCommissionConf;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

public interface IUserCommissionConfService extends IValueObjectService<UserCommissionConf> {
    public List<UserCommissionConf> findByUserId(int uid);

    public List<UserCommissionConf> findByUidAccount(Map<String,Object> params);

    public int doDeleteByUserId(int userId);

    public List<UserCommissionConf> findByUserIdAndAccount(int uid,int account);

	public UserCommissionConf findUserCommissionConfByRulesId(String string);
}