package com.fx.user.dao;

import com.fx.user.model.UserContacts;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IUserContactsDao extends IValueObjectDao<UserContacts> {

    public List<UserContacts> findByUserId(Integer id);

    public int queryCountByCondition(Map<String, Object> params);

    public List<UserContacts> queryByCondition(Map<String, Object> params);

    public UserContacts findMainContactByUserId(Integer uid);
}