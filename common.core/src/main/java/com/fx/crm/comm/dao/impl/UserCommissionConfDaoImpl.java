package com.fx.crm.comm.dao.impl;

import com.fx.crm.comm.dao.IUserCommissionConfDao;
import com.fx.crm.comm.model.UserCommissionConf;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserCommissionConfDaoImpl extends BaseDao<UserCommissionConf> implements IUserCommissionConfDao {

    public UserCommissionConfDaoImpl() {
        super(IUserCommissionConfDao.class.getName());
    }

    @Override
    public List<UserCommissionConf> findByUserId(int uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", uid+"");
        return findList("findByUserId", params);
    }
    @Override
    public List<UserCommissionConf> findByUserIdAndAccount(int uid, int account) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", uid+"");
        params.put("commAccount", account+"");
        return findList("findByUserIdAndAccount", params);
    }
}