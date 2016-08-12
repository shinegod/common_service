package com.fx.user.dao;

import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.user.model.Complaint;

public interface IComplaintDao extends IValueObjectDao<Complaint> {
	/**
	 * getByUid	根据Uid获取投诉详细信息
	 * @param uid
	 * @return User
	 * @exception 
	*/
	List<Complaint> getByUid(int uid);
}