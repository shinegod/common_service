package com.fx.common.service.impl;

import com.fx.common.dao.IIndustryDao;
import com.fx.common.model.Industry;
import com.fx.common.service.IIndustryService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndustryServiceImpl extends BaseVOService<Industry> implements IIndustryService {
    @Autowired
    private IIndustryDao industryDao;
}