package com.fx.user.service;

import java.util.List;

import com.fx.user.model.Cancellation;

import mybatis.framework.core.service.IValueObjectService;

public interface ICancellationService extends IValueObjectService<Cancellation> {
	
	/**
	 * getByUid	根据Uid获取用户信息
	 * @param uid
	 * @return Cancellation
	 * @exception 
	*/
	public List<Cancellation> getByUid(int uid);
}