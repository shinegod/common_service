package com.fx.giftsActivity.dao.impl;

import com.fx.giftsActivity.dao.IGiftsActivityDao;
import com.fx.giftsActivity.model.GiftsActivity;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GiftsActivityDaoImpl extends BaseDao<GiftsActivity> implements IGiftsActivityDao {

    public GiftsActivityDaoImpl() {
        super(IGiftsActivityDao.class.getName());
    }

    @Override
    public List<GiftsActivity> getByCondition(HashMap<String, Object> params) {
        return findList("getByCondition", params);
    }

    @Override
    public List<GiftsActivity> getGiftsNameByActivityName(Map<String, Object> params) {
        return findList("getGiftsNameByActivityName",params);
    }

    @Override
    public List<GiftsActivity> queryListByConditionTrader(HashMap<String, Object> params) {
        return findList("queryListByConditionTrader", params);
    }

    @Override
    public List<GiftsActivity> getByDateCondition(HashMap<String, Object> params) {
        return findList("getByDateCondition", params);
    }
}