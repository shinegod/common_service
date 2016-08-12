package com.fx.common.service.impl;

import com.fx.common.dao.ICityDao;
import com.fx.common.model.City;
import com.fx.common.service.ICityService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CityServiceImpl extends BaseVOService<City> implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Override
    public List<City> findByPorvinceId(int provinceId) {
        return cityDao.findByPorvinceId(provinceId);
    }

	@Override
	public List<City> findByProvinceIdList(HashMap<String, Object> params) {
		return cityDao.findList("findByProvinceIdList", params);
	}
}