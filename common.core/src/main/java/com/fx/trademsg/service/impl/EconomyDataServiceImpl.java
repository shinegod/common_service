package com.fx.trademsg.service.impl;

import com.fx.trademsg.dao.IEconomyDataDao;
import com.fx.trademsg.model.EconomyData;
import com.fx.trademsg.service.IEconomyDataService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EconomyDataServiceImpl extends BaseVOService<EconomyData> implements IEconomyDataService {
    @Autowired
    private IEconomyDataDao economyDataDao;
}