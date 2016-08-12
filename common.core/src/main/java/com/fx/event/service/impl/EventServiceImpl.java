package com.fx.event.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.event.dao.IEventDao;
import com.fx.event.model.Event;
import com.fx.event.service.IEventService;
import com.fx.user.model.MT4Transfer;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends BaseVOService<Event> implements IEventService {
    @Autowired
    private IEventDao eventDao;

	@Override
	public PageIterator<Event> pageQueryByConditionByIsIb(int status,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("type", status);
		}
		int totalCount = eventDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<Event> eventList = eventDao.pageQueryByCondition(params);
		PageIterator<Event> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(eventList);
		return page;
	}
}