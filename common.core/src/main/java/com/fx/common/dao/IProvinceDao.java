package com.fx.common.dao;

import com.fx.common.model.Province;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IProvinceDao extends IValueObjectDao<Province> {
    public List<Province> findByCountryId(int countryId);
}