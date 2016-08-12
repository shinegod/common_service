package com.fx.user.service.impl;

import java.util.List;

import com.fx.user.dao.IInvestExperienceDao;
import com.fx.user.model.InvestExperience;
import com.fx.user.service.IInvestExperienceService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.InvertibleComparator;

@Service
public class InvestExperienceServiceImpl extends BaseVOService<InvestExperience> implements IInvestExperienceService {
    @Autowired
    private IInvestExperienceDao investExperienceDao;

	@Override
	public InvestExperience getByUid(int uid) {
		return investExperienceDao.getByUid(uid);
	}
	
	@Override
	public List<InvestExperience> getByUidList(List<Integer> uidList) {
		if(null == uidList || uidList.size() <= 0){
			return null;
		}
		return investExperienceDao.getByUidList(uidList);
	}
}