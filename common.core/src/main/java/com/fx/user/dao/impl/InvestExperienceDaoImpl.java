package com.fx.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.user.dao.IInvestExperienceDao;
import com.fx.user.model.InvestExperience;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class InvestExperienceDaoImpl extends BaseDao<InvestExperience> implements IInvestExperienceDao {

    public InvestExperienceDaoImpl() {
        super( IInvestExperienceDao.class.getName());
    }

	@Override
	public InvestExperience getByUid(int uid) {
		return (InvestExperience) findOne("getByUid", uid);
	}

	@Override
	public List<InvestExperience> getByUidList(List<Integer> uidList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uidList", uidList);
		return super.findList("getByUidList", params);
	}
}