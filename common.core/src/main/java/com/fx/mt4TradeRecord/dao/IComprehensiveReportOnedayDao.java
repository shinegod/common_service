package com.fx.mt4TradeRecord.dao;

import java.util.List;

import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;

import com.fx.mt4TradeRecord.model.Mt4Users;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IComprehensiveReportOnedayDao extends IValueObjectDao<ComprehensiveReportOneday> {

	List<ComprehensiveReportOneday> getByTimeLoginEarly(String interviewDate1,
			List<Integer> login);

	List<ComprehensiveReportOneday> getByTimeLogin(String interviewDate1,
			String interviewDate2, List<Integer> login);

	ComprehensiveReportOneday getByTimeLoginEarlyTotal(String interviewDate1, List<Integer> login);

	ComprehensiveReportOneday getByTimeLoginTotal(String interviewDate1, String interviewDate2, List<Integer> login);
}