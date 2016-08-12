package com.fx.news.service;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.news.model.News;

public interface INewsService extends IValueObjectService<News> {
	
	public PageIterator<News> pageQueryByCondition(int pageNo, int pageSize, Map<String, Object> params);

	public News findByTitle(String name);

	public List<News> findListByCondition(News newq);
}