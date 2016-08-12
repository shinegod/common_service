package com.fx.event.dao.impl;

import java.util.List;
import java.util.Map;

import com.fx.event.dao.IEventDao;
import com.fx.event.model.Event;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements IEventDao {

    public EventDaoImpl() {
        super(IEventDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<Event> pageQueryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
}