package com.fx.giftsActivity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.giftsActivity.dao.IGiftsActivityDao;
import com.fx.giftsActivity.model.GiftsActivity;
import com.fx.giftsActivity.service.IGiftsActivityService;

import com.fx.giftsUser.model.GiftsUser;
import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftsActivityServiceImpl extends BaseVOService<GiftsActivity> implements IGiftsActivityService {
    @Autowired
    private IGiftsActivityDao giftsActivityDao;

	@Override
	public List<GiftsActivity> queryListByCondition(
			HashMap<String, Object> params) {
		return giftsActivityDao.findList("queryListByCondition", params);
	}

	@Override
	public List<GiftsActivity> getGiftsActivityByDataSourceUidbefore(HashMap<String, Object> params) {
		return giftsActivityDao.findList("getGiftsActivityByDataSourceUidbefore", params);
	}

	@Override
	public List<GiftsActivity> getGiftsActivityByDataSourceUidnow(HashMap<String, Object> params) {
		return giftsActivityDao.findList("getGiftsActivityByDataSourceUidnow", params);
	}

	@Override
	public List<GiftsActivity> queryListByAvailable(
			HashMap<String, Object> params) {
		return giftsActivityDao.findList("queryListByAvailable", params);
	}

	@Override
	public List<GiftsActivity> getByDateCondition(HashMap<String, Object> params) {
		List<GiftsActivity> giftsActivityList = giftsActivityDao.getByDateCondition(params);
		return giftsActivityList;
	}

	@Override
	public HashMap<Integer, GiftsActivity> getByCondition(List<GiftsUser> giftsUserList) {
		List<Integer> gids = new ArrayList<>();
		for (GiftsUser giftsUser : giftsUserList){
			gids.add(giftsUser.getGid());
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (giftsUserList != null && giftsUserList.size()>0){
			params.put("gids", gids);
		}
		List<GiftsActivity> giftsActivityList = giftsActivityDao.getByCondition(params);
		HashMap<Integer, GiftsActivity> map = new HashMap<Integer, GiftsActivity>();
		if (giftsActivityList != null && giftsActivityList.size() > 0){
			for (GiftsActivity giftsActivity : giftsActivityList){
				map.put(giftsActivity.getId(),giftsActivity);
			}
		}
		return map;
	}

	@Override
	public List<GiftsActivity> getGiftsNameByActivityName(String activityName) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("activityName", activityName);
		return giftsActivityDao.getGiftsNameByActivityName(params);
	}

	@Override
	public List<GiftsActivity> queryListByConditionTrader(HashMap<String, Object> params) {
		return giftsActivityDao.queryListByConditionTrader(params);
	}

	@Override
	public List<GiftsActivity> getGiftsActivityByDataSourceUid(HashMap<String, Object> params) {
		return giftsActivityDao.findList("getGiftsActivityByDataSourceUid", params);
	}

	@Override
	public List<GiftsActivity> getGiftsActivityByDataSourceUidnowApply(HashMap<String, Object> params) {
		return giftsActivityDao.findList("getGiftsActivityByDataSourceUidnowApply", params);
	}
}