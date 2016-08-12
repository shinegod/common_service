package com.fx.common.service.impl;

import com.fx.common.dao.IProvinceDao;
import com.fx.common.model.Province;
import com.fx.common.service.IProvinceService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl extends BaseVOService<Province> implements IProvinceService {
    @Autowired
    private IProvinceDao provinceDao;

    @Override
    public List<Province> findByCountryId(int countryId) {
        return provinceDao.findByCountryId(countryId);
    }
}