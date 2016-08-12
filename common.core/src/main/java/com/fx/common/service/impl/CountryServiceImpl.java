package com.fx.common.service.impl;

import com.fx.common.dao.ICountryDao;
import com.fx.common.model.Country;
import com.fx.common.service.ICountryService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends BaseVOService<Country> implements ICountryService {
    @Autowired
    private ICountryDao countryDao;
}