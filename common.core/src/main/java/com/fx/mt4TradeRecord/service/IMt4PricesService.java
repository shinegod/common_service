package com.fx.mt4TradeRecord.service;

import com.fx.mt4TradeRecord.model.Mt4Prices;

import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IMt4PricesService extends IValueObjectService<Mt4Prices> {

    public List<Mt4Prices> selectAllByDataSourceName(int dataSourceId);

	public Mt4Prices getMt4PricesBySymbol(String symbol,int dataSourceId);
}