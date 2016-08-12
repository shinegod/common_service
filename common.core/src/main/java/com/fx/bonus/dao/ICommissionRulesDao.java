package com.fx.bonus.dao;

import com.fx.bonus.model.CommissionRules;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICommissionRulesDao extends IValueObjectDao<CommissionRules> {

	List<CommissionRules> findAllByStatus(int typeid);

	public void updatestaById(int id);

	public List<CommissionRules> findAllByStatus();

	public List<CommissionRules> findByDataSourceId(int dataSourceId);
}