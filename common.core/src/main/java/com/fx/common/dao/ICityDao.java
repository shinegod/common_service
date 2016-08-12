package com.fx.common.dao;

import com.fx.common.model.City;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICityDao extends IValueObjectDao<City> {
    public List<City> findByPorvinceId(int provinceId);
}