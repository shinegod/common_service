package com.fx.user.dao;

import java.util.List;
import java.util.Map;

import com.fx.user.model.EDoc;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IEDocDao extends IValueObjectDao<EDoc> {

	/**
	 * getByUid	根据用户UID获取用户电子证件信息
	 * @param uid
	 * @return EDoc
	 * @exception 
	*/
	public List<EDoc> getByUid(int uid);

	/**
	 * getByUidList	根据用户UID批量获取用户电子证件信息
	 * @param uidList
	 * @return List<EDoc>
	 * @exception 
	*/
	public List<EDoc> getByUidList(List<Integer> uidList);

	public List<EDoc> getByUidAndType(Map map);
	
	public List<EDoc> findListByUserId(Integer userId);
}