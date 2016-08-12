package com.fx.giftsActivity.service;

import java.util.HashMap;
import java.util.List;

import com.fx.giftsActivity.model.GiftsActivity;

import com.fx.giftsUser.model.GiftsUser;
import mybatis.framework.core.service.IValueObjectService;

public interface IGiftsActivityService extends IValueObjectService<GiftsActivity> {
	
	/**
	 * 赠金赠品活动列表
	 * @param params
	 * @return
	 */
	List<GiftsActivity> queryListByCondition(HashMap<String, Object> params);
	
	/**
	 * 可参加的活动
	 * @param params
	 * @return
	 */
	List<GiftsActivity> queryListByAvailable(HashMap<String, Object> params);

	HashMap<Integer, GiftsActivity> getByCondition(List<GiftsUser> giftsUserList);

	List<GiftsActivity> queryListByConditionTrader(HashMap<String, Object> params);

	List<GiftsActivity> getGiftsActivityByDataSourceUid(HashMap<String, Object> params);

	List<GiftsActivity> getGiftsActivityByDataSourceUidnow(HashMap<String, Object> params);

	List<GiftsActivity> getGiftsActivityByDataSourceUidbefore(HashMap<String, Object> params);

	List<GiftsActivity> getByDateCondition(HashMap<String, Object> params);

	List<GiftsActivity> getGiftsNameByActivityName(String activityName);

	List<GiftsActivity> getGiftsActivityByDataSourceUidnowApply(HashMap<String, Object> params);
}