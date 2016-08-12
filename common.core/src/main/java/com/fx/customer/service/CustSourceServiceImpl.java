package com.fx.customer.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.customer.model.CustCategory;
import mybatis.framework.core.support.Page;

import com.fx.customer.model.CustSource;

public class CustSourceServiceImpl implements ICustSourceService {

	@Override
	public CustSource findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustSource> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doDeleteById(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doInsert(CustSource vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdateById(CustSource vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page pagedQuery(int pageNo, int pageSize,
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doInsertList(List<CustSource> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustSource> CustSourceAll() {
		return null;
	}

	@Override
	public void insertCustSource(String name) {

	}

	@Override
	public void updateCustSource(int id, String name) {

	}

	@Override
	public void deleteCustSource(int id) {

	}
}
