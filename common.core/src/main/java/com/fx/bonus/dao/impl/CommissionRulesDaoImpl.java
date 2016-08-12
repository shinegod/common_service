package com.fx.bonus.dao.impl;

import com.fx.bonus.dao.ICommissionRulesDao;
import com.fx.bonus.model.CommissionRules;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommissionRulesDaoImpl extends BaseDao<CommissionRules> implements ICommissionRulesDao {

    public CommissionRulesDaoImpl() {
        super(ICommissionRulesDao.class.getName());
    }

	@Override
	public List<CommissionRules> findAllByStatus(int typeid) {
		Map map = new HashMap();
		map.put("typeid",typeid);
		return findList("findAllByStatus",map);
	}

	@Override
	public void updatestaById(int id) {
		Map m = new HashMap();
		m.put("id",id);
		update("updatestaById",m);
	}

	@Override
	public List<CommissionRules> findAllByStatus() {
		return findList("findAllByStatus1",null);
	}

	@Override
	public List<CommissionRules> findByDataSourceId(int dataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("dataSourceId", dataSourceId);
		return findList("findByDataSourceId", params);
	}
}