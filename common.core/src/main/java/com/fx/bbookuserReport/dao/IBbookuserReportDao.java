package com.fx.bbookuserReport.dao;

import com.fx.bbookuserReport.model.BbookuserReport;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IBbookuserReportDao extends IValueObjectDao<BbookuserReport> {

    public List<BbookuserReport> selectBycreateDate(String date);

    public BbookuserReport selectByLoginAndDate(Map map);
}