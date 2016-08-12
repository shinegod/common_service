package com.fx.news.service.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.news.dao.INewsDao;
import com.fx.news.model.News;
import com.fx.news.service.INewsService;

@Service
public class NewsServiceImpl extends BaseVOService<News> implements INewsService {
    @Autowired
    private INewsDao newsDao;

	@Override
	public PageIterator<News> pageQueryByCondition(int pageNo, int pageSize,Map<String, Object> params) {
		int totalCount = newsDao.pageQueryListCount(params);
		List<News> list =  this.newsDao.pageQueryList(pageNo, pageSize, params);
		PageIterator<News> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}

	@Override
	public News findByTitle(String name) {
		return (News) newsDao.findOne("findByTitle",name);

	}

	@Override
	public List<News> findListByCondition(News newq) {
		return newsDao.findList("findListByCondition",newq);
	}
}