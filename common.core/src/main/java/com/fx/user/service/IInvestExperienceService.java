package com.fx.user.service;

import java.util.List;

import com.fx.user.model.InvestExperience;

import mybatis.framework.core.service.IValueObjectService;

public interface IInvestExperienceService extends IValueObjectService<InvestExperience> {
	
	/**
	 * getByUid	根据uid获取用户投资经验信息
	 * @param uid
	 * @return List<InvestExperience>
	 * @exception 
	*/
	public InvestExperience getByUid(int uid);
	
	/**
	 * getByUidList	管理后台使用，根据用户UID批量获取投资经验信息
	 * @param uidList
	 * @return List<InvestExperience>
	 * @exception 
	*/
	public List<InvestExperience> getByUidList(List<Integer> uidList);
}