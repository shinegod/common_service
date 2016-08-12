package com.fx.news.dao;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.news.model.News;

public interface INewsDao extends IValueObjectDao<News> {
	
	List<News> pageQueryList(int pageNo, int pageSize,Map<String, Object> params);
	
	int pageQueryListCount(Map<String, Object> params);
}