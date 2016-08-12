package com.fx.tradeAdjust.service.impl;

import com.fx.tradeAdjust.dao.ITradeAdjustDao;
import com.fx.tradeAdjust.model.TradeAdjust;
import com.fx.tradeAdjust.service.ITradeAdjustService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeAdjustServiceImpl extends BaseVOService<TradeAdjust> implements ITradeAdjustService {
    @Autowired
    private ITradeAdjustDao tradeAdjustDao;
}