package com.fx.news.dao.impl;

import java.util.List;
import java.util.Map;

import com.fx.news.dao.INewsDao;
import com.fx.news.model.News;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends BaseDao<News> implements INewsDao {

    public NewsDaoImpl() {
        super(INewsDao.class.getName());
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<News> pageQueryList(int pageNo, int pageSize,
			Map<String, Object> params) {
		int offset = (pageNo-1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		return (List<News>)findList("pageQueryList", params);
	}

	@Override
	public int pageQueryListCount(Map<String, Object> params) {
		return (Integer)findOne("pageQueryListCount", params);
	}
}