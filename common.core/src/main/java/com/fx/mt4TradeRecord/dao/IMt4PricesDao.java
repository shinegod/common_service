package com.fx.mt4TradeRecord.dao;

import com.fx.mt4TradeRecord.model.Mt4Prices;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IMt4PricesDao extends IValueObjectDao<Mt4Prices> {

    public List<Mt4Prices> selectAllByDataSourceName();

	public Mt4Prices getMt4PricesBySymbol(String symbol);
}