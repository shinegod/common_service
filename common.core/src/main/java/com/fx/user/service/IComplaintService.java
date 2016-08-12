package com.fx.user.service;

import java.util.List;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.user.model.Complaint;

public interface IComplaintService extends IValueObjectService<Complaint> {
	/**
	 * getByUid	根据Uid获取投诉信息
	 * @param uid
	 * @return User
	 * @exception 
	*/
	public List<Complaint> getByUid(int uid);
}