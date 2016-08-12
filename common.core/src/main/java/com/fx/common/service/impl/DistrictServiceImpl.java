package com.fx.common.service.impl;

import com.fx.common.dao.IDistrictDao;
import com.fx.common.model.District;
import com.fx.common.service.IDistrictService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl extends BaseVOService<District> implements IDistrictService {
    @Autowired
    private IDistrictDao districtDao;
}