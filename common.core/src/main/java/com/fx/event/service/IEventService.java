package com.fx.event.service;

import com.fx.event.model.Event;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IEventService extends IValueObjectService<Event> {
	public PageIterator<Event> pageQueryByConditionByIsIb(int status, int pageNo, int pageSize);
}