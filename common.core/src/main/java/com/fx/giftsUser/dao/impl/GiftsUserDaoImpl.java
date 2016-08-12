package com.fx.giftsUser.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.giftsUser.dao.IGiftsUserDao;
import com.fx.giftsUser.model.GiftsUser;

import java.util.HashMap;
import java.util.List;

@Repository
public class GiftsUserDaoImpl extends BaseDao<GiftsUser> implements IGiftsUserDao {

    public GiftsUserDaoImpl() {
        super(IGiftsUserDao.class.getName());
    }

    @Override
    public List<GiftsUser> getByCondition(HashMap<String, Object> params) {
        return findList("getByCondition",params);
    }

    @Override
    public List<GiftsUser> getByUidCondition(HashMap<String, Object> params) {
        return findList("getByUidCondition",params);
    }
}