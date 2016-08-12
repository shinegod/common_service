package com.fx.mt4TradeRecord.service.impl;

import com.fx.mt4TradeRecord.dao.IMt4PricesDao;
import com.fx.mt4TradeRecord.model.Mt4Prices;
import com.fx.mt4TradeRecord.service.IMt4PricesService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mt4PricesServiceImpl extends BaseVOService<Mt4Prices> implements IMt4PricesService {
    @Autowired
    private IMt4PricesDao mt4PricesDao;

    @Override
    public List<Mt4Prices> selectAllByDataSourceName(int dataSourceId) {
        multiDataSourceSet(dataSourceId);
        return mt4PricesDao.selectAllByDataSourceName();
    }

	@Override
	public Mt4Prices getMt4PricesBySymbol(String symbol, int dataSourceId) {
		multiDataSourceSet(dataSourceId);
        return mt4PricesDao.getMt4PricesBySymbol(symbol);
	}
}