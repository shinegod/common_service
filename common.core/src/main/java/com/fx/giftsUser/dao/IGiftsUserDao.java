package com.fx.giftsUser.dao;

import com.fx.giftsUser.model.GiftsUser;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;

public interface IGiftsUserDao extends IValueObjectDao<GiftsUser> {
    List<GiftsUser> getByCondition(HashMap<String, Object> params);

    List<GiftsUser> getByUidCondition(HashMap<String, Object> params);
}