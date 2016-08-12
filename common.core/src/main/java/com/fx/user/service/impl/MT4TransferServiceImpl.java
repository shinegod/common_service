package com.fx.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.user.dao.IMT4TransferDao;
import com.fx.user.model.MT4Transfer;
import com.fx.user.service.IMT4TransferService;

import com.google.common.collect.Maps;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MT4TransferServiceImpl extends BaseVOService<MT4Transfer> implements IMT4TransferService {
    @Autowired
    private IMT4TransferDao mT4TransferDao;

	@Override
	public PageIterator<MT4Transfer> pageQueryByConditionByIsIb(int status,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		int totalCount = mT4TransferDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<MT4Transfer> mt4TransferList = mT4TransferDao.pageQueryByCondition(params);
		PageIterator<MT4Transfer> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(mt4TransferList);
		return page;
	}

	@Override
	public PageIterator<MT4Transfer> pageQueryByConditionByUserId(
			Integer userId, int status, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		params.put("uid", userId);
		int totalCount = mT4TransferDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<MT4Transfer> mt4TransferList = mT4TransferDao.pageQueryByCondition(params);
		PageIterator<MT4Transfer> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(mt4TransferList);
		return page;
	}

	@Override
	public List<MT4Transfer> queryListByCondition(HashMap<String, Object> params) {
		return mT4TransferDao.queryListByCondition(params);
	}

    @Override
	public List<MT4Transfer> findbyUserId(Integer uid){
		return mT4TransferDao.queryListbyUid(uid);
	}

	@Override
	public List<MT4Transfer> queryListByStatus(HashMap<String, Object> params) {
		return mT4TransferDao.queryListByStatus(params);
	}

	@Override
	public double findTotalAmout(Integer mt4Accont) {
		return mT4TransferDao.findTotalByMt4Account(mt4Accont);
	}


}