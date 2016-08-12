package com.fx.common.service;

import com.fx.common.model.City;

import mybatis.framework.core.service.IValueObjectService;

import java.util.HashMap;
import java.util.List;

public interface ICityService extends IValueObjectService<City> {
    public List<City> findByPorvinceId(int provinceId);
    
    /**
     * 根据多个省份的Id查找city
     * @param params
     * @return
     */
	public List<City> findByProvinceIdList(HashMap<String, Object> params);
}