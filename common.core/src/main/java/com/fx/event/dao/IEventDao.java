package com.fx.event.dao;

import java.util.List;
import java.util.Map;

import com.fx.event.model.Event;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IEventDao extends IValueObjectDao<Event> {

	public int queryCountByCondition(Map<String, Object> params);

	public List<Event> pageQueryByCondition(Map<String, Object> params);
}