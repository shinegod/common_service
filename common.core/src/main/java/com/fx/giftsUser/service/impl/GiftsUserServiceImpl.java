package com.fx.giftsUser.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.giftsUser.dao.IGiftsUserDao;
import com.fx.giftsUser.model.GiftsUser;
import com.fx.giftsUser.service.IGiftsUserService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftsUserServiceImpl extends BaseVOService<GiftsUser> implements IGiftsUserService {
    @Autowired
    private IGiftsUserDao giftsUserDao;

	@Override
	public List<Integer> getGidsByUid(Integer id) {
		return giftsUserDao.findList("getGidsByUid", id);
	}

	@Override
	public int doDeleteByUid(int id) {
		return giftsUserDao.doUpdate("doDeleteByUid", id);
	}

	@Override
	public List<GiftsUser> findByGid(int id) {
		return giftsUserDao.findList("findByGid", id);
	}

	@Override
	public List<GiftsUser> getByUidDate(HashMap<String, Object> params) {
		return giftsUserDao.findList("getByUidDate",params);
	}

	@Override
	public List<GiftsUser> getByCondition(HashMap<String, Object> params) {
		return giftsUserDao.getByCondition(params);
	}

	@Override
	public HashMap<Integer, GiftsUser> getByUidCondition(HashMap<String, Object> params) {
		List<GiftsUser> giftsUserList = giftsUserDao.getByUidCondition(params);
		HashMap<Integer, GiftsUser> map = new HashMap<Integer, GiftsUser>();
		if (giftsUserList != null && giftsUserList.size() > 0){
			for (GiftsUser giftsUser : giftsUserList){
				map.put(giftsUser.getGid(),giftsUser);
			}
		}
		return map;
	}

}