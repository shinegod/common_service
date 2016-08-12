package com.fx.mt4TradeRecord.service.impl;

import java.util.List;

import com.fx.mt4TradeRecord.dao.IComprehensiveReportOnedayDao;
import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;
import com.fx.mt4TradeRecord.service.IComprehensiveReportOnedayService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprehensiveReportOnedayServiceImpl extends BaseVOService<ComprehensiveReportOneday> implements IComprehensiveReportOnedayService {
    @Autowired
    private IComprehensiveReportOnedayDao comprehensiveReportOnedayDao;

	@Override
	public List<ComprehensiveReportOneday> getByTimeLoginEarly(
			String interviewDate1, List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return comprehensiveReportOnedayDao.getByTimeLoginEarly(interviewDate1,login);
	}

	@Override
	public List<ComprehensiveReportOneday> getByTimeLogin(
			String interviewDate1, String interviewDate2, List<Integer> login,
			Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return comprehensiveReportOnedayDao.getByTimeLogin(interviewDate1,interviewDate2,login);
	}

	@Override
	public ComprehensiveReportOneday getByTimeLoginEarlyTotal(String interviewDate1, List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return comprehensiveReportOnedayDao.getByTimeLoginEarlyTotal(interviewDate1, login);
	}

	@Override
	public ComprehensiveReportOneday getByTimeLoginTotal(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId) {
		multiDataSourceSet(dataSourceId);
		return comprehensiveReportOnedayDao.getByTimeLoginTotal(interviewDate1,interviewDate2,login);
	}
}