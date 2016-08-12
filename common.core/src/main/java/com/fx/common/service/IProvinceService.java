package com.fx.common.service;

import com.fx.common.model.Province;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IProvinceService extends IValueObjectService<Province> {
    public List<Province> findByCountryId(int countryId);
}