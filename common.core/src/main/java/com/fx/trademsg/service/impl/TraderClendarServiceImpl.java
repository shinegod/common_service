package com.fx.trademsg.service.impl;

import com.fx.trademsg.dao.ITraderClendarDao;
import com.fx.trademsg.model.TraderClendar;
import com.fx.trademsg.service.ITraderClendarService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderClendarServiceImpl extends BaseVOService<TraderClendar> implements ITraderClendarService {
    @Autowired
    private ITraderClendarDao traderClendarDao;
}