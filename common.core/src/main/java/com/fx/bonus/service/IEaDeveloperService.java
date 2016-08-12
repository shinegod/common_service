package com.fx.bonus.service;

import java.util.Map;

import com.fx.bonus.model.EaDeveloper;
import com.fx.user.model.User;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IEaDeveloperService extends IValueObjectService<EaDeveloper> {
	PageIterator<EaDeveloper> pageQueryByCondition(int pageNo,
												   int pageSize, Map<String, Object> params);
	public EaDeveloper getByUid(int uid);
	EaDeveloper getByMt4Account(Integer mt4Account);
}