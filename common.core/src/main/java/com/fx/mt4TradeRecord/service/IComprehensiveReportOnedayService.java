package com.fx.mt4TradeRecord.service;

import java.util.List;

import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;

import com.fx.mt4TradeRecord.model.Mt4Users;
import mybatis.framework.core.service.IValueObjectService;

public interface IComprehensiveReportOnedayService extends IValueObjectService<ComprehensiveReportOneday> {

	List<ComprehensiveReportOneday> getByTimeLoginEarly(String interviewDate1,
			List<Integer> login, Integer dataSourceId);

	List<ComprehensiveReportOneday> getByTimeLogin(String interviewDate1,
			String interviewDate2, List<Integer> login, Integer dataSourceId);
	ComprehensiveReportOneday getByTimeLoginEarlyTotal(String interviewDate1, List<Integer> login, Integer dataSourceId);

	ComprehensiveReportOneday getByTimeLoginTotal(String interviewDate1, String interviewDate2, List<Integer> login, Integer dataSourceId);

}