package com.fx.crm.comm.dao;

import com.fx.crm.comm.model.UserCommissionConf;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IUserCommissionConfDao extends IValueObjectDao<UserCommissionConf> {
    public List<UserCommissionConf> findByUserId(int uid);
    public List<UserCommissionConf> findByUserIdAndAccount(int uid,int account);
}