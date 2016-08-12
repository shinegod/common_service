package com.fx.mt4TradeRecord.dao.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.dao.BaseDao;
import mybatis.framework.util.DBContants;

import org.springframework.stereotype.Repository;

import com.fx.mt4TradeRecord.dao.IMt4PricesDao;
import com.fx.mt4TradeRecord.model.Mt4Prices;

@Repository
public class Mt4PricesDaoImpl extends BaseDao<Mt4Prices> implements IMt4PricesDao {

    public Mt4PricesDaoImpl() {
        super(DBContants.DB_MT4TRADE,IMt4PricesDao.class.getName());
    }

    @Override
    public List<Mt4Prices> selectAllByDataSourceName() {
        return findList("selectAllByDataSourceName","");
    }

	@Override
	public Mt4Prices getMt4PricesBySymbol(String symbol) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		return (Mt4Prices)findOne("selectByPrimaryKey", params);
	}
}