package com.fx.bonus.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ITradingGroupDao;
import com.fx.bonus.model.TradingGroup;

import java.util.List;
import java.util.Map;

@Repository
public class TradingGroupDaoImpl extends BaseDao<TradingGroup> implements ITradingGroupDao {

    public TradingGroupDaoImpl() {
        super(ITradingGroupDao.class.getName());
    }

    public void deleteTradingGroup(int id){
        update("deleteTradingGroup",id);
    }

    @Override
    public List<TradingGroup> selectTradingGroupBydataSourceId(int dataSourceId) {
        return findList("selectTradingGroupBydataSourceId",dataSourceId);
    }

	@Override
	public int queryCountByConditionByDataSource(Map<String, Object> params) {
		return (Integer) super.findOne("queryCountByConditionByDataSource", params);
	}

	@Override
	public List<TradingGroup> queryByConditionByDataSource(
			Map<String, Object> params) {
		return findList("queryByConditionByDataSource", params);
	}
}