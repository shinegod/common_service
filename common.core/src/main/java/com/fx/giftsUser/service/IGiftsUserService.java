package com.fx.giftsUser.service;

import java.util.HashMap;
import java.util.List;

import com.fx.giftsUser.model.GiftsUser;

import mybatis.framework.core.service.IValueObjectService;

public interface IGiftsUserService extends IValueObjectService<GiftsUser> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Integer> getGidsByUid(Integer id);

	public int doDeleteByUid(int id);

	public List<GiftsUser> findByGid(int id);

	List<GiftsUser> getByUidDate(HashMap<String, Object> params);

	List<GiftsUser> getByCondition(HashMap<String, Object> params);

	HashMap<Integer,GiftsUser> getByUidCondition(HashMap<String, Object> params);
}