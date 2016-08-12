package com.fx.common.dao.impl;

import com.fx.common.dao.ICityDao;
import com.fx.common.model.City;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityDaoImpl extends BaseDao<City> implements ICityDao {

    public CityDaoImpl() {
        super(ICityDao.class.getName());
    }

    @Override
    public List<City> findByPorvinceId(int provinceId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("provinceId", provinceId);
        return findList("findByPorvinceId", params);
    }
}