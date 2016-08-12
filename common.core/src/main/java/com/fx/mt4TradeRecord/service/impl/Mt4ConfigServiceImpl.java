package com.fx.mt4TradeRecord.service.impl;

import com.fx.mt4TradeRecord.dao.IMt4ConfigDao;
import com.fx.mt4TradeRecord.model.Mt4Config;
import com.fx.mt4TradeRecord.service.IMt4ConfigService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mt4ConfigServiceImpl extends BaseVOService<Mt4Config> implements IMt4ConfigService {
    @Autowired
    private IMt4ConfigDao mt4ConfigDao;
}