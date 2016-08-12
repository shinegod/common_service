package com.fx.bonus.dao;

import java.util.List;
import java.util.Map;

import com.fx.bonus.model.EaDeveloper;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IEaDeveloperDao extends IValueObjectDao<EaDeveloper> {
	int pageQueryListCount(Map<String, Object> params);

	List<EaDeveloper> pageQueryList(int pageNo, int pageSize,
									Map<String, Object> params);
	public EaDeveloper getByUid(int uid);
	public EaDeveloper getByMt4Account(Integer mt4Account);
}