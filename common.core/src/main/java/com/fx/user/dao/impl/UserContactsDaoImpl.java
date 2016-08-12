package com.fx.user.dao.impl;

import com.fx.user.dao.IUserContactsDao;
import com.fx.user.model.UserContacts;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserContactsDaoImpl extends BaseDao<UserContacts> implements IUserContactsDao {

    public UserContactsDaoImpl() {
        super(IUserContactsDao.class.getName());
    }

    @Override
    public List<UserContacts> findByUserId(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", id);
        return findList("queryListByUserId", params);
    }

    @Override
    public int queryCountByCondition(Map<String, Object> params) {
        return (Integer)findOne("pageQueryCount", params);
    }

    @Override
    public List<UserContacts> queryByCondition(Map<String, Object> params) {
        return findList("pageQueryList", params);
    }

    @Override
    public UserContacts findMainContactByUserId(Integer uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", uid);
        return (UserContacts)findOne("findMainContactByUserId", params);
    }

}