package com.fx.bonus.service.impl;

import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.IEaDeveloperDao;
import com.fx.bonus.model.EaDeveloper;
import com.fx.bonus.service.IEaDeveloperService;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaDeveloperServiceImpl extends BaseVOService<EaDeveloper> implements IEaDeveloperService {
    @Autowired
    private IEaDeveloperDao eaDeveloperDao;

	@Override
	public PageIterator<EaDeveloper> pageQueryByCondition(int pageNo,
			int pageSize, Map<String, Object> params) {
		int totalCount = eaDeveloperDao.pageQueryListCount(params);
		List<EaDeveloper> list =  eaDeveloperDao.pageQueryList(pageNo, pageSize, params);
		PageIterator<EaDeveloper> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}
	@Override
	public EaDeveloper getByUid(int uid) {
		return eaDeveloperDao.getByUid(uid);
	}
	@Override
	public EaDeveloper getByMt4Account(Integer mt4Account) {
		return eaDeveloperDao.getByMt4Account(mt4Account);
	}
}