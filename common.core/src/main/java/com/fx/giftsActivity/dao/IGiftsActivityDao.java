package com.fx.giftsActivity.dao;

import com.fx.giftsActivity.model.GiftsActivity;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IGiftsActivityDao extends IValueObjectDao<GiftsActivity> {
    List<GiftsActivity> getByCondition(HashMap<String, Object> params);

    List<GiftsActivity> queryListByConditionTrader(HashMap<String, Object> params);

    List<GiftsActivity> getByDateCondition(HashMap<String, Object> params);

    List<GiftsActivity> getGiftsNameByActivityName(Map<String, Object> params);
}