package com.fx.user.dao.impl;

import java.util.List;
import java.util.Map;

import com.fx.user.dao.ITradeQueryDao;
import com.fx.user.model.TradeQuery;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class TradeQueryDaoImpl extends BaseDao<TradeQuery> implements ITradeQueryDao {

    public TradeQueryDaoImpl() {
        super(ITradeQueryDao.class.getName());
    }

	@Override
	public List<TradeQuery> getByUid(int uid) {
		return (List<TradeQuery>)super.findOne("getByUid", uid);
	}
	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}
	@Override
	public List<TradeQuery> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
}