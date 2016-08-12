package com.fx.user.dao;

import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.user.model.Cancellation;

public interface ICancellationDao extends IValueObjectDao<Cancellation> {
	
	/**
	 * getByUid	根据Uid获取用户详细信息
	 * @param uid
	 * @return Cancellation
	 * @exception 
	*/
	List<Cancellation> getByUid(int uid);
}