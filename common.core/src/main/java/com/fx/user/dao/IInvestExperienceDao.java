package com.fx.user.dao;

import java.util.List;

import com.fx.user.model.InvestExperience;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IInvestExperienceDao extends IValueObjectDao<InvestExperience> {

	/**
	 * getByUid	根据用户uid获取投资经验
	 * @param uid
	 * @return InvestExperience
	 * @exception 
	*/
	InvestExperience getByUid(int uid);

	/**
	 * getByUidList	根据用户UID批量获取投资经验信息
	 * @param uidList
	 * @return List<InvestExperience>
	 * @exception 
	*/
	List<InvestExperience> getByUidList(List<Integer> uidList);
}